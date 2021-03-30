import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.*;

public class MyFrame extends JFrame {
    ResultSet result;
    Connection conn = null;
    PreparedStatement state = null;
    JTabbedPane tab = new JTabbedPane();
    JPanel upPanel = new JPanel();
    JPanel midPanel = new JPanel();
    JPanel downPanel = new JPanel();
    //upPanel........................................................................................
    JLabel fnameL = new JLabel("Име:");
    JLabel lnameL = new JLabel("Фамилия:");
    JLabel type = new JLabel("Вид на тревата:");
    JLabel buyerNumber = new JLabel("Номер на купувач:");
    JLabel weedNumber = new JLabel("Номер на тревата:");
    JLabel orderNumber = new JLabel("Номер на поръчката:");
    JLabel orderQuantity = new JLabel("Количество на поръчката:");
    JLabel numberWeed = new JLabel(" Номер на тревата:");
    JLabel numberBuyer = new JLabel("Номер на копувача:");
    JLabel scrolW = new JLabel("Scroll:");
    JLabel scrolB = new JLabel("Scroll:");
    JLabel scrolO =new JLabel("Scroll:");
    JTable table = new JTable();
    JTable table1 = new JTable();
    JTable table2 = new JTable();
    JScrollPane myScroll = new JScrollPane(table);
    JScrollPane myScroll1 = new JScrollPane(table1);
    JScrollPane myScroll2 = new JScrollPane(table2);

    JTextField nweedTF = new JTextField();
    JTextField nbuyerTF = new JTextField();
    JTextField fnameTF = new JTextField();
    JTextField lnameTF = new JTextField();
    JTextField bnumberTF = new JTextField();
    JTextField weedTypeTF = new JTextField();
    JTextField wnumberTF = new JTextField();
    JTextField onumberTF = new JTextField();
    JTextField quantityTF = new JTextField();

    // midPanel........................................................................................
    JButton addBuyer = new JButton("Добавяне");
    JButton deleteBuyer = new JButton("Изтриване");
    JButton editBuyer = new JButton("Редактиране");
    JButton addWeed = new JButton("Добавяне");
    JButton deleteWeed = new JButton("Изтриване");
    JButton editWeed = new JButton("Редактиране");
    JButton addOrder = new JButton("Добавяне");
    JButton deleteOrder = new JButton("Изтриване");
    JButton editOrder = new JButton("Редактиране");

    // dounPanel ......................................................................................

    public MyFrame() {
        this.setSize(400, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);


        upPanel.setLayout(new GridLayout(5, 2));
        midPanel.setLayout(new GridLayout(6, 1));
        downPanel.setLayout(new GridLayout(7, 2));
        //upPanel --------------------------------------------------------------
        tab.addTab("WeedType", upPanel);
        upPanel.add(numberWeed );
        upPanel.add(nweedTF);
        upPanel.add(type);
        upPanel.add(weedTypeTF);
        upPanel.add(scrolW);
        upPanel.add(myScroll);
        refreshTable("WEED",table);
        upPanel.add(addWeed);
        upPanel.add(deleteWeed);
        upPanel.add(editWeed);
        addWeed.addActionListener(new AddActionWeed());
        deleteWeed.addActionListener(new DeleteActionWeed());
        // midPanel -------------------------------------------------------------
        tab.addTab("Buyers", midPanel);
        midPanel.add(numberBuyer);
        midPanel.add(nbuyerTF);
        midPanel.add(fnameL);
        midPanel.add(fnameTF);
        midPanel.add(lnameL);
        midPanel.add(lnameTF);
        midPanel.add(scrolB);
        midPanel.add(myScroll1);
        refreshTable("CUSTOMERS",table1);
        midPanel.add(addBuyer);
        midPanel.add(deleteBuyer);
        midPanel.add(editBuyer);
        addBuyer.addActionListener(new AddActionCustomers());
        deleteBuyer.addActionListener(new DeleteActionCustomers());
        // downPanel ------------------------------------------------------------
        tab.addTab("Orders", downPanel);
        downPanel.add(buyerNumber);
        downPanel.add(bnumberTF);
        downPanel.add(weedNumber);
        downPanel.add(wnumberTF);
        downPanel.add(orderNumber);
        downPanel.add(onumberTF);
        downPanel.add(orderQuantity);
        downPanel.add(quantityTF);
        downPanel.add(scrolO);
        downPanel.add(myScroll2);
        refreshTable("ORDERS",table2);
        downPanel.add(addOrder);
        downPanel.add(deleteOrder);
        downPanel.add(editOrder);
        addOrder.addActionListener(new AddActionOrders());
        deleteOrder.addActionListener(new DeleteActionOrders());
        this.add(tab);
        this.setVisible(true);
    }// край конструктор
    public void refreshTable(String myTable, JTable table){
        conn = DBConnection.getConnection();
        try {
            state = conn.prepareStatement("select * from "+myTable);
            result = state.executeQuery();
            table.setModel(new MyModel(result));
        } catch (Exception e){
            e.printStackTrace();
        }
    }



    class AddActionCustomers implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            conn = DBConnection.getConnection();
            String sql = "insert into CUSTOMERS values(?,?,?)";

            try {
                state = conn.prepareStatement(sql);
                state.setInt(1, Integer.parseInt(nbuyerTF.getText()));
                state.setString(2, fnameTF.getText());
                state.setString(3, lnameTF.getText());


                state.execute();
                refreshTable("CUSTOMERS",table1);


            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }
    class AddActionWeed implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            conn = DBConnection.getConnection();
            String sql = "insert into WEED values(?,?)";

            try {
                state = conn.prepareStatement(sql);
                state.setInt(1, Integer.parseInt(nweedTF.getText()));
                state.setString(2, weedTypeTF.getText());



                state.execute();
                refreshTable("WEED",table);

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }
    class AddActionOrders implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            conn = DBConnection.getConnection();
            String sql = "insert into ORDERS values(?,?,?,?)";

            try {
                state = conn.prepareStatement(sql);
                state.setInt(1, Integer.parseInt(onumberTF.getText()));
                state.setString(2, wnumberTF.getText());
                state.setString(3, onumberTF .getText());
                state.setString(4, quantityTF.getText());


                state.execute();
                refreshTable("ORDERS",table2);

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }
    class DeleteActionWeed implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent arg0) {
            conn=DBConnection.getConnection();
            String sql="delete from WEED where WEED_ID=?";

            try {
                state=conn.prepareStatement(sql);
                state.setInt(1, Integer.parseInt(nweedTF.getText()));
                state.execute();

                refreshTable("WEED",table);

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }
    class DeleteActionCustomers implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent arg0) {
            conn=DBConnection.getConnection();
            String sql="delete from CUSTOMERS where CUSTOMERS_ID=?";

            try {
                state=conn.prepareStatement(sql);
                state.setInt(1, Integer.parseInt(nbuyerTF.getText()));
                state.execute();

                refreshTable("CUSTOMERS",table1);

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }
    class DeleteActionOrders implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent arg0) {
            conn=DBConnection.getConnection();
            String sql="delete from ORDERS where ORDERS_ID=?";

            try {
                state=conn.prepareStatement(sql);
                state.setInt(1, Integer.parseInt(bnumberTF.getText()));
                state.execute();

                refreshTable("ORDERS",table2);

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }
}
