import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Dictionary extends JFrame {
    public Dictionary() {
        setTitle("TERNARY SEARCH TREE");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel label = new JLabel("DICTIONARY");
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        label.setFont(new Font(label.getFont().getName(), Font.BOLD, label.getFont().getSize()));
        label.setBounds(235, 20, 300, 20);
        panel.add(label);

        JButton button1 = new JButton("Kiểm Tra Chính Tả");
        button1.setFont(new Font("Arial", Font.PLAIN, 13));
        button1.setFont(new Font(button1.getFont().getName(), Font.BOLD, button1.getFont().getSize()));
        button1.setBounds(70, 70, 200, 30);
        panel.add(button1);

        JButton button2 = new JButton("Tìm Kiếm Từ Theo Tiền Tố");
        button2.setFont(new Font("Arial", Font.PLAIN, 13));
        button2.setFont(new Font(button2.getFont().getName(), Font.BOLD, button2.getFont().getSize()));
        button2.setBounds(320, 70, 200, 30);
        panel.add(button2);

        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(70, 120, 450, 300);
        panel.add(scrollPane);
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                TernarySearchTree tst = new TernarySearchTree();
                String[] words = tst.ReadFile();
                for (String word : words) {
                    tst.insert(word);
                }
                String prefix = JOptionPane.showInputDialog("Mời Nhập Từ Cần Kiểm Tra : ");
                prefix = prefix.toLowerCase();
                System.out.println(tst.search(prefix));
                if(tst.search(prefix)){
                    textArea.append("ĐÚNG");
                } else {
                    textArea.append("SAI");
                }
//                textArea.append(tst.search(prefix) + "\n");
            }
        });

        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                String prefix = JOptionPane.showInputDialog("Mời Nhập Tiền Tố Của Từ Cần Kiếm : ");
                prefix = prefix.toLowerCase();
                textArea.append("KẾT QUẢ : \"" + prefix + "\": " + OutputSearch(prefix) + "\n");
            }
        });

        add(panel);
        setVisible(true);
    }
    private static String OutputSearch(String prefix){
        String output = "\n";
        TernarySearchTree tst = new TernarySearchTree();
        String[] words = tst.ReadFile();
        List<String> outputResult = null;
        for (String word : words) {
            tst.insert(word);
            List<String> result = tst.prefixSearch(prefix);
            outputResult = result;
        }
        for(String i: outputResult){
            output += i + "\n";
        }

        return output;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Dictionary frame = new Dictionary();
            }
        });
    }
}