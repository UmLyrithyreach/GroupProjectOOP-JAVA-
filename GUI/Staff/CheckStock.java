package Staff;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Class.DatabaseConnection;

public class CheckStock {
    private JFrame frame;
    private JTable stockTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;
    private JComboBox<String> filterComboBox;
    
    public CheckStock() {
        initialize();
        loadStockData();
    }
    
    private void initialize() {
        frame = new JFrame("Check Shirt Stock");
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        // Create table model with columns
        String[] columns = {"ID", "Name", "Brand", "Size", "Style", "Stock", "Supplier ID"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table read-only
            }
        };
        
        // Create table and set properties
        stockTable = new JTable(tableModel);
        stockTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        stockTable.setRowHeight(25);
        stockTable.getTableHeader().setReorderingAllowed(false);
        stockTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        
        // Create scroll pane for table
        JScrollPane scrollPane = new JScrollPane(stockTable);
        frame.add(scrollPane, BorderLayout.CENTER);
        
        // Create top panel for title
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Shirt Stock Inventory", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        topPanel.add(titleLabel, BorderLayout.CENTER);
        
        // Create back button
        JLabel backLabel = new JLabel("Back");
        backLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        backLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 0));
        backLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
            }
        });
        topPanel.add(backLabel, BorderLayout.WEST);
        
        frame.add(topPanel, BorderLayout.NORTH);
        
        // Create bottom panel for search and filter
        JPanel bottomPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Search field
        JPanel searchPanel = new JPanel(new BorderLayout());
        JLabel searchLabel = new JLabel("Search: ");
        searchField = new JTextField();
        searchPanel.add(searchLabel, BorderLayout.WEST);
        searchPanel.add(searchField, BorderLayout.CENTER);
        bottomPanel.add(searchPanel);
        
        // Filter dropdown
        JPanel filterPanel = new JPanel(new BorderLayout());
        JLabel filterLabel = new JLabel("Filter by: ");
        String[] filterOptions = {"All", "Low Stock (< 10)", "Out of Stock (0)", "Well Stocked (≥ 20)"};
        filterComboBox = new JComboBox<>(filterOptions);
        filterPanel.add(filterLabel, BorderLayout.WEST);
        filterPanel.add(filterComboBox, BorderLayout.CENTER);
        bottomPanel.add(filterPanel);
        
        // Search button
        JButton searchButton = new JButton("Search/Filter");
        searchButton.addActionListener(e -> applySearchAndFilter());
        bottomPanel.add(searchButton);
        
        frame.add(bottomPanel, BorderLayout.SOUTH);
        
        // Make the frame visible
        frame.setVisible(true);
    }
    
    private void loadStockData() {
        // Clear the table
        tableModel.setRowCount(0);
        
        // SQL query to get all shirts with their stock levels
        String query = "SELECT c.id, c.name, c.brand, c.size, c.style, c.stock, c.supplierId " +
                       "FROM clothes c " +
                       "ORDER BY c.name";
                       
        try (PreparedStatement stmt = DatabaseConnection.executePreparedQuery(query);
             ResultSet rs = stmt.executeQuery()) {
            
            if (!rs.isBeforeFirst()) {
                JOptionPane.showMessageDialog(frame, "No shirts found in the database.");
                return;
            }
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String brand = rs.getString("brand");
                String size = rs.getString("size");
                String style = rs.getString("style");
                int stock = rs.getInt("stock");
                int supplierId = rs.getInt("supplierId");
                
                // Add row to the table model
                tableModel.addRow(new Object[]{id, name, brand, size, style, stock, supplierId});
            }
            
            // Show info dialog with total count
            int totalRows = tableModel.getRowCount();
            JOptionPane.showMessageDialog(frame, 
                "Loaded " + totalRows + " shirt items from inventory database.");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, 
                "Error loading stock data: " + e.getMessage(), 
                "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    private void applySearchAndFilter() {
        String searchText = searchField.getText().toLowerCase().trim();
        String filterOption = (String) filterComboBox.getSelectedItem();
        
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        stockTable.setRowSorter(sorter);
        
        List<RowFilter<Object, Object>> filters = new ArrayList<>();
        
        // Add search filter if search text is not empty
        if (!searchText.isEmpty()) {
            RowFilter<Object, Object> searchFilter = new RowFilter<Object, Object>() {
                @Override
                public boolean include(Entry<? extends Object, ? extends Object> entry) {
                    for (int i = 0; i < entry.getValueCount(); i++) {
                        if (entry.getStringValue(i).toLowerCase().contains(searchText)) {
                            return true;
                        }
                    }
                    return false;
                }
            };
            filters.add(searchFilter);
        }
        
        // Add stock level filter if not "All"
        if (filterOption != null && !filterOption.equals("All")) {
            RowFilter<Object, Object> stockFilter = new RowFilter<Object, Object>() {
                @Override
                public boolean include(Entry<? extends Object, ? extends Object> entry) {
                    int stockLevel = Integer.parseInt(entry.getStringValue(5));
                    
                    switch (filterOption) {
                        case "Low Stock (< 10)":
                            return stockLevel > 0 && stockLevel < 10;
                        case "Out of Stock (0)":
                            return stockLevel == 0;
                        case "Well Stocked (≥ 20)":
                            return stockLevel >= 20;
                        default:
                            return true;
                    }
                }
            };
            filters.add(stockFilter);
        }
        
        // Apply the filters
        if (!filters.isEmpty()) {
            RowFilter<Object, Object> combinedFilter = RowFilter.andFilter(filters);
            sorter.setRowFilter(combinedFilter);
            
            // Show filtered count
            int filteredCount = stockTable.getRowCount();
            JOptionPane.showMessageDialog(frame, 
                "Found " + filteredCount + " items matching your criteria.");
        } else {
            // Clear filters
            sorter.setRowFilter(null);
        }
    }
    
    // Method to get the selected shirt's data (useful if you need to pass data to another screen)
    public Object[] getSelectedShirtData() {
        int selectedRow = stockTable.getSelectedRow();
        if (selectedRow != -1) {
            selectedRow = stockTable.convertRowIndexToModel(selectedRow);
            
            Object[] rowData = new Object[7];
            for (int i = 0; i < 7; i++) {
                rowData[i] = tableModel.getValueAt(selectedRow, i);
            }
            return rowData;
        }
        return null;
    }
    
    // Method to highlight low stock items (can be called to visually indicate items that need attention)
    public void highlightLowStockItems() {
        stockTable.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, 
                    boolean isSelected, boolean hasFocus, int row, int column) {
                
                Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);
                
                int modelRow = table.convertRowIndexToModel(row);
                int stock = Integer.parseInt(tableModel.getValueAt(modelRow, 5).toString());
                
                if (stock == 0) {
                    c.setBackground(new Color(255, 200, 200)); // Light red for out of stock
                } else if (stock < 10) {
                    c.setBackground(new Color(255, 255, 200)); // Light yellow for low stock
                } else {
                    c.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
                }
                
                return c;
            }
        });
        
        stockTable.repaint();
    }
    
    // You can add this method if you want to open the CheckStock window from another class
    public static void openStockCheckWindow() {
        SwingUtilities.invokeLater(() -> new CheckStock());
    }
}