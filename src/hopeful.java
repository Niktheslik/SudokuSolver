import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import java.awt.TextArea;


public class hopeful extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	//private JTextArea textArea= new JTextArea();
	private final TextArea textArea_1 = new TextArea();
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					hopeful frame = new hopeful();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public hopeful() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{141,141, 141};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 252, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 2;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 3;
		gbc_textField_1.gridy = 0;
		contentPane.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JButton btnSolveAnNxn = new JButton("Solve an NxN board");
		btnSolveAnNxn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea_1.setText(null);
				Sudoku sud = new Sudoku();
				String s = sud.fileToString(textField.getText());
				int n = s.length();
				n = (int) Math.sqrt(n);
				sud = new Sudoku(n);
				sud.size = n;
				sud.solve(s, sud);
				
			}
		});
		
		JButton btnGenerateASudoku = new JButton("Generate a Sudoku Board");
		btnGenerateASudoku.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea_1.setText(null);
				Sudoku sud = new Sudoku();
				sud.generatePuzzle();
			}
		});
		GridBagConstraints gbc_btnGenerateASudoku = new GridBagConstraints();
		gbc_btnGenerateASudoku.anchor = GridBagConstraints.NORTH;
		gbc_btnGenerateASudoku.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnGenerateASudoku.insets = new Insets(0, 0, 5, 5);
		gbc_btnGenerateASudoku.gridx = 0;
		gbc_btnGenerateASudoku.gridy = 1;
		contentPane.add(btnGenerateASudoku, gbc_btnGenerateASudoku);
		GridBagConstraints gbc_btnSolveAnNxn = new GridBagConstraints();
		gbc_btnSolveAnNxn.gridwidth = 2;
		gbc_btnSolveAnNxn.anchor = GridBagConstraints.NORTH;
		gbc_btnSolveAnNxn.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSolveAnNxn.insets = new Insets(0, 0, 5, 5);
		gbc_btnSolveAnNxn.gridx = 1;
		gbc_btnSolveAnNxn.gridy = 1;
		contentPane.add(btnSolveAnNxn, gbc_btnSolveAnNxn);
		
		JButton btnSolveASaurai = new JButton("Solve a Samurai Board");
		btnSolveASaurai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea_1.setText(null);
				Samurai sam = new Samurai();
				String s = sam.fileToString(textField_1.getText());
				sam.solve(s, sam);
			}
		});
		
		GridBagConstraints gbc_btnSolveASaurai = new GridBagConstraints();
		gbc_btnSolveASaurai.insets = new Insets(0, 0, 5, 0);
		gbc_btnSolveASaurai.anchor = GridBagConstraints.NORTH;
		gbc_btnSolveASaurai.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSolveASaurai.gridx = 3;
		gbc_btnSolveASaurai.gridy = 1;
		contentPane.add(btnSolveASaurai, gbc_btnSolveASaurai);
		GridBagConstraints gbc_textArea_1 = new GridBagConstraints();
		gbc_textArea_1.gridwidth = 4;
		gbc_textArea_1.gridheight = 3;
		gbc_textArea_1.fill = GridBagConstraints.VERTICAL;
		gbc_textArea_1.insets = new Insets(0, 0, 5, 5);
		gbc_textArea_1.gridx = 0;
		gbc_textArea_1.gridy = 2;
		textArea_1.setEditable(false);
		contentPane.add(textArea_1, gbc_textArea_1);
		
		redirectSystemStreams();
	}
	
	private void updateTextArea(final String text) {
        SwingUtilities.invokeLater(new Runnable() {
      

		public void run() {
              textArea_1.append(text);
          }
        });
      }

      private void redirectSystemStreams() {
        OutputStream out = new OutputStream() {
          @Override
          public void write(int b) throws IOException {
            updateTextArea(String.valueOf((char) b));
          }

          @Override
          public void write(byte[] b, int off, int len) throws IOException {
            updateTextArea(new String(b, off, len));
          }

          @Override
          public void write(byte[] b) throws IOException {
            write(b, 0, b.length);
          }
        };

        System.setOut(new PrintStream(out, true));
        System.setErr(new PrintStream(out, true));
      }

}
