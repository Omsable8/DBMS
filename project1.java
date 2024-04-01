import javax.swing.*;

import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.*;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.sql.*;

public class project1 {

    public static void main(String[] args)throws ClassNotFoundException,SQLException {

        SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame("Restaurant Table Management");

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            frame.setSize(800, 600);
            frame.setResizable(false);

            try {

                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            } catch (Exception e) {

                e.printStackTrace();

            }



            frame.getContentPane().setBackground(new Color(173, 216, 230));


            JPanel mainPanel = new JPanel(new BorderLayout());

            mainPanel.setBackground(Color.lightGray);



            JPanel tableSelectionPanel = new JPanel();

            tableSelectionPanel.setLayout(new GridLayout(0, 3, 10, 10));

            tableSelectionPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            tableSelectionPanel.setBackground(Color.lightGray);



            //stores table number and model
            HashMap<Integer, DefaultListModel<Item>> tableLists = new HashMap<>(); 

            //to generate 10 table buttons
            for (int i = 1; i <= 10; i++) {

                final int tableNumber = i;

                JButton tableButton = new JButton("Table " + tableNumber);

                tableButton.setFont(new Font("Arial", Font.BOLD, 14));

                tableButton.setBackground(Color.LIGHT_GRAY);

                tableButton.setForeground(Color.BLACK);

                tableButton.setFocusPainted(false);

                tableButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                tableButton.setActionCommand("Table_" + tableNumber);

                tableButton.addActionListener(e -> {

                    JOptionPane.showMessageDialog(frame, "Table " + tableNumber + " selected.");

                    DefaultListModel<Item> listModel = tableLists.get(tableNumber);

                    if (listModel == null) {

                        listModel = new DefaultListModel<>();

                        tableLists.put(tableNumber, listModel);

                    }

                    updateFoodItemsList(listModel);

                });

                tableSelectionPanel.add(tableButton);

            }



            mainPanel.add(tableSelectionPanel, BorderLayout.SOUTH);



            JPanel orderDetailsPanel = new JPanel();

            orderDetailsPanel.setLayout(new BoxLayout(orderDetailsPanel, BoxLayout.Y_AXIS));

            orderDetailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            orderDetailsPanel.setBackground(Color.DARK_GRAY);



            JButton addItemButton = new JButton("Add Item");

            JButton deleteItemButton = new JButton("Delete Item");

            JButton generateBillButton = new JButton("Generate Bill");



            addItemButton.setFont(new Font("Arial", Font.PLAIN, 14));

            deleteItemButton.setFont(new Font("Arial", Font.PLAIN, 14));

            generateBillButton.setFont(new Font("Arial", Font.PLAIN, 14));



            final JPopupMenu menu = new JPopupMenu();

            JMenuItem menuItem1 = new JMenuItem("Pizza");

            JMenuItem menuItem2 = new JMenuItem("Pasta");

            menu.add(menuItem1);

            menu.add(menuItem2);



            addItemButton.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent ev) {

                    menu.show(addItemButton, addItemButton.getBounds().x, addItemButton.getBounds().y + addItemButton.getBounds().height);

                }

            });


            DefaultListModel<Item> listModel = new DefaultListModel<>();

            JList<Item> foodItemsList = new JList<>(listModel);


            menuItem1.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {

                    String quantity = JOptionPane.showInputDialog(frame, "Enter quantity for Pizza:");

                    if (quantity != null && !quantity.isEmpty()) {

                        for (int i = 0; i < Integer.parseInt(quantity); i++) {

                            listModel.addElement(new Item("Pizza", 200));

                        }
                        try {
							addItem(1,"Pizza",Integer.parseInt(quantity),200);
						} catch (NumberFormatException e1) {
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							e1.printStackTrace();
						} 
						catch(SQLException e1) {
							System.out.println(e1.getMessage());
						}
                        

                    }
                    

                }

            });
            

            menuItem2.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {

                    String quantity = JOptionPane.showInputDialog(frame, "Enter quantity for Pasta:");

                    if (quantity != null && !quantity.isEmpty()) {

                        for (int i = 0; i < Integer.parseInt(quantity); i++) {

                            listModel.addElement(new Item("Pasta", 150));

                        }
                        try {
     						addItem(1,"Pasta",Integer.parseInt(quantity),150);
     						} catch (NumberFormatException e1) {
     							e1.printStackTrace();
     						} catch (ClassNotFoundException e1) {
     							e1.printStackTrace();
     						} catch (SQLException e1) {
     							e1.printStackTrace();
     						}

                    }

                }

            });


            deleteItemButton.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {

                    Item selectedItem = foodItemsList.getSelectedValue();

                    if (selectedItem != null) {

                        listModel.removeElement(selectedItem);
                        
                        try {
							deleteItem(1,selectedItem.name,selectedItem.price);
						} catch (ClassNotFoundException e1) {
							
							e1.printStackTrace();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}

                    }

                }

            });


            //TO BE EDITED
            generateBillButton.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {

                    double total = 0;

                    for (int i = 0; i < listModel.size(); i++) {

                        Item item = listModel.getElementAt(i);

                        total += item.price;

                    }

                    JOptionPane.showMessageDialog(frame, "Total Bill: $" + total);
                    try {
						Topdf(1);
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

                }

            });



            orderDetailsPanel.add(addItemButton);

            orderDetailsPanel.add(Box.createRigidArea(new Dimension(0, 10)));

            orderDetailsPanel.add(deleteItemButton);

            orderDetailsPanel.add(Box.createRigidArea(new Dimension(0, 10)));

            orderDetailsPanel.add(generateBillButton);



            mainPanel.add(orderDetailsPanel, BorderLayout.CENTER);



            foodItemsList.setCellRenderer(new DefaultListCellRenderer() {

                @Override

                public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

                    JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                    if (value instanceof Item) {

                        Item item = (Item) value;

                        label.setText(item.name + " - Rs" + item.price);

                    }

                    return label;

                }

            });



            JScrollPane scrollPane = new JScrollPane(foodItemsList);

            scrollPane.setPreferredSize(new Dimension(200, 400));



            JPanel foodItemsPanel = new JPanel();

            foodItemsPanel.setOpaque(true);

            foodItemsPanel.setBackground(Color.black);

            foodItemsPanel.add(scrollPane);



            mainPanel.add(foodItemsPanel, BorderLayout.LINE_END);



            frame.add(mainPanel);

            frame.setVisible(true);

        });

    }


    //TO BE EDITED
    private static void updateFoodItemsList(DefaultListModel<Item> listModel) {



    }


	
    public static class Item {

        String name;

        int price;



        public Item(String name, int price) {

            this.name = name;

            this.price = price;

        }



        @Override

        public String toString() {

            return name + " - $" + price;

        }

    }
//SQL QUERIES
	public static void addItem(int table,String name,int qty,int price)throws ClassNotFoundException,SQLException {
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel","root","1234");
			Statement stmt=con.createStatement();
			String query="";
			if(getQty(table,name) == 0) {
				 query = "insert into table" +(table)+ " values('"+(name)+"',"+(qty)+","+(price)+","+(price*qty)+");";
			}
			else {
				query = "update table" +(table)+ " set quantity = quantity+"+(qty)+" ,total=total+"+(price*qty)+" where name =  '"+(name)+"';" ;
			}
			stmt.executeUpdate(query);		
			con.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
    public static void deleteItem(int table,String name,int price)throws ClassNotFoundException,SQLException {
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel","root","1234");
			Statement stmt=con.createStatement();
			String query="";
			if(getQty(table,name) == 1) {
				query = "delete from table" +(table)+ " where name =  '"+(name)+"';" ;
			}
			else {
				query = "update table" +(table)+ " set quantity = quantity - "+(1)+" ,total=total -"+(price)+" where name =  '"+(name)+"';" ;
			}
			stmt.executeUpdate(query);		
			con.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
    public static int getQty(int table,String name)throws ClassNotFoundException,SQLException {
		int ans=0;
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel","root","1234");
			Statement stmt=con.createStatement();
			String query = "select * from table"+(table)+" where name= '"+name+"';";
			ResultSet rs= stmt.executeQuery(query);
			
			if(rs.next()) {
				ans= rs.getInt(2);
			}
			con.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return ans;
	}
    public static void Topdf(int table)throws ClassNotFoundException,SQLException {
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotel","root","1234");
			Statement stmt=con.createStatement();
			String query = "select * from table"+(table)+";";
			ResultSet rs= stmt.executeQuery(query);
			
			Document my_pdf_report = new Document();
            PdfWriter.getInstance(my_pdf_report, new FileOutputStream("BILL.pdf"));
            my_pdf_report.open();            
         
            PdfPTable my_report_table = new PdfPTable(4);
           
            PdfPCell table_cell;
            table_cell = new PdfPCell(new Phrase("Name"));
            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase("Quantity"));
            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase("Price"));
            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase("Total"));
            my_report_table.addCell(table_cell);
             
			 while (rs.next()) {                
                 String name = rs.getString("name");
                 
                 table_cell = new PdfPCell(new Phrase(name));
                 my_report_table.addCell(table_cell);
                 String qty= Integer.toString(rs.getInt(2));
                 table_cell = new PdfPCell(new Phrase(qty));
                 my_report_table.addCell(table_cell);
                 String price= Integer.toString(rs.getInt(3));
                 table_cell = new PdfPCell(new Phrase(price));
                 my_report_table.addCell(table_cell);
                 String total = Integer.toString(rs.getInt(4));
                 table_cell = new PdfPCell(new Phrase(total));
                 my_report_table.addCell(table_cell);
            }
			 my_pdf_report.add(my_report_table);                       
	         my_pdf_report.close();
			 con.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
}