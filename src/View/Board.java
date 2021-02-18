/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.BoardLogic;
import Model.*;
import Model.Constants.*;
import Model.Enum.*;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.Border;

/**
 * A tic-tac-toe game made using Java Swing. Features : 1) Number of rows and
 * columns are generated based on user input. 2) Players can choose their own
 * name and symbol used in the game. Use default players if selected "No". 3)
 * Can choose the number of players in the game.
 *
 * @author Andre
 */
public class Board extends JFrame implements ActionListener {

    //Set to the largest number
    private int ROW = BoardConstants.INITIAL_ROW_VALUE;
    private int COL = BoardConstants.INITIAL_COL_VALUE;

    private BoardLogic boardLogic;
    private JButton[][] symbolButtons = new JButton[ROW][COL];
    private JButton startButton = new JButton(BoardConstants.START_BUTTON_MESSAGE);
    private JLabel notification = new JLabel("");

    private List<Player> players = new ArrayList<>();
    private Player currentPlayer;

    private Integer totalPlayers;
    private Integer playerTurn = 1; // Initial Player Turn

    public Board() {
        refreshBoardPanel();
        showDialogPlayerCountAndBoardSize();
        restartBoard();
        startUpPanels();
    }

    public void refreshBoardPanel(){
        boardPlay = new JPanel();
        boardPlay.setLayout(new BoxLayout(boardPlay, BoxLayout.Y_AXIS));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        boardPlay = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tic Tac Toe Test");

        boardPlay.setToolTipText("");
        boardPlay.setName("PlayBoard"); // NOI18N

        javax.swing.GroupLayout boardPlayLayout = new javax.swing.GroupLayout(boardPlay);
        boardPlay.setLayout(boardPlayLayout);
        boardPlayLayout.setHorizontalGroup(
            boardPlayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
        );
        boardPlayLayout.setVerticalGroup(
            boardPlayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 278, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(boardPlay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(boardPlay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Board.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Board.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Board.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Board.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Board().setVisible(true);
            }
        });
    }

    private void customizePlayers() {
        refreshBoardPanel();
        //For configurable player count
        for (int i = 1; i <= totalPlayers; i++) {
            boardPlay.add(addPlayerCustomizationPanel(i));
        }

        int result = JOptionPane.showConfirmDialog(null, boardPlay,
                BoardConstants.PLAYER_CUSTOMIZATION_MESSAGE,
                JOptionPane.YES_OPTION);
        //Loop JPanels in the panel and get its components.
        if (result == JOptionPane.OK_OPTION) {
            Component[] components = boardPlay.getComponents();
            for (Component com : components) {
                if (com instanceof JPanel) {
                    Component[] comChildren = ((JPanel) com).getComponents();
                    String chosenPlayerName = "";
                    Symbol chosenSymbol = Symbol.O;
                    for (Component comChild : comChildren) {
                        if (comChild instanceof JTextField) {
                            chosenPlayerName = ((JTextField) comChild).getText();
                        }
                        if (comChild instanceof JComboBox) {
                            String symbolString = ((JComboBox) comChild).getSelectedItem().toString();
                            chosenSymbol = Symbol.valueOf(symbolString);
                        }
                    }
                    Player player = new Player((players.size() + 1),
                            chosenPlayerName,
                            chosenSymbol);
                    players.add(player);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, BoardConstants.USE_DEFAULT_PLAYER_MESSAGE);
            populateDefaultPlayers();
        }
    }

    private void populateDefaultPlayers() {
        Player player1 = new Player(1, BoardConstants.DEFAULT_PLAYER1_NAME, Symbol.X);
        Player player2 = new Player(2, BoardConstants.DEFAULT_PLAYER2_NAME, Symbol.O);
        players.add(player1);
        players.add(player2);
    }

    private JPanel addPlayerCustomizationPanel(Integer iteration) {
        JPanel subPanel = new JPanel();
        JTextField nameField = new JTextField(BoardConstants.PLAYER_NAME_MAX_CHARACTER);
        nameField.setName(BoardConstants.NAME_CUSTOMIZATION_LABEL_PREFIX + BoardConstants.DEFAULT_PLAYER_NAME_PREFIX);
        nameField.setText(BoardConstants.DEFAULT_PLAYER_NAME_PREFIX + iteration);
        
        JComboBox symbolChoice = new JComboBox(Symbol.values());
        symbolChoice.setName(BoardConstants.SYMBOL_CUSTOMIZATION_PREFIX + BoardConstants.DEFAULT_PLAYER_NAME_PREFIX);
        symbolChoice.setSelectedIndex(iteration - 1);

        Border border = BorderFactory.createTitledBorder(BoardConstants.DEFAULT_PLAYER_NAME_PREFIX + iteration);
        subPanel.setBorder(border);
        subPanel.add(new JLabel("Name : "));
        subPanel.add(nameField);
        subPanel.add(Box.createVerticalStrut(15));
        subPanel.add(Box.createHorizontalStrut(15));
        subPanel.add(new JLabel("Symbol :"));
        subPanel.add(symbolChoice);
        return subPanel;
    }

    private void showDialogPlayerCountAndBoardSize() {
        JPanel subPanel = new JPanel();
        subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.Y_AXIS));
        
        SpinnerNumberModel boardSizeModel = new SpinnerNumberModel(3, 3, 10, 1);
        SpinnerNumberModel playerCountModel = new SpinnerNumberModel(2, 2, 10, 1);
        
        JSpinner boardSizeSpinner = new JSpinner(boardSizeModel);
        JSpinner playerCountSpinner = new JSpinner(playerCountModel);
        
        subPanel.add(new JLabel(BoardConstants.BOARD_SIZE_SPINNER_MESSAGE));
        subPanel.add(boardSizeSpinner);
        subPanel.add(new JLabel(BoardConstants.PLAYER_COUNT_SPINNER_MESSAGE));
        subPanel.add(playerCountSpinner);
        
        int option = JOptionPane.showOptionDialog(null,
                subPanel,
                BoardConstants.BOARD_SPINNERS_MESSAGE,
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, null, null);

        if (option == JOptionPane.OK_OPTION) {
            int chosenNumber = (int) boardSizeSpinner.getValue();
            ROW = chosenNumber;
            COL = chosenNumber;
            totalPlayers = (int) playerCountSpinner.getValue();
        } else if (option == JOptionPane.CANCEL_OPTION) {
            System.exit(0);
        }
    }

    private void clearBoard(String string) {
        setNotification(string);
        setButtons(false);
        startButton.setVisible(true);

        playerTurn = 1;
        currentPlayer = new Player();
        players.clear();
        restartBoard();
    }

    private void turnPlay() {
        if (startButton.isVisible()) {
            customizePlayers();
        }
        currentPlayer = players.stream()
                .filter(pla -> (playerTurn == pla.getId()))
                .findFirst()
                .get();
        setNotification(currentPlayer.getName() + BoardConstants.NEW_TURN_MESSAGE 
                + currentPlayer.getCharacter().getSymbolString());

        playerTurn++;
        if (playerTurn > players.size()) {
            playerTurn = 1;
        }
        if (boardLogic.getTurn() <= 1) {
            setButtons(true);
        }
    }

    private void afterClick(int row, int col) {
        if (boardLogic.getBoardValue(row, col) == null) {
            symbolButtons[row][col].setText(currentPlayer.getCharacter().getSymbolString());
            boardLogic.setBoardValue(row, col, currentPlayer.getCharacter());
            boolean isGameOver = isGameOver(row, col);
            if (!isGameOver) {
                turnPlay();
            }
        }
    }

    private boolean isGameOver(int row, int col) {
        //Don't need to search the whole board to determine a winner,
        //just need to search the rows, cols, and diagonals from the cell
        //of the last move.
        if (boardLogic.isWinner(row, col, currentPlayer)) {
            clearBoard(currentPlayer.getName() + State.WINNER.getMessage());
            return true;
        }
        if (boardLogic.isDraw()) {
            clearBoard(State.DRAW.getMessage());
            return true;
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == startButton) {
            turnPlay();
            startButton.setVisible(false);
        } else {
            for (int i = 0; i < ROW; i++) {
                for (int j = 0; j < COL; j++) {
                    if (event.getSource() == symbolButtons[i][j]) {
                        afterClick(i, j);
                    }
                }
            }
        }
    }

    private void setNotification(String string) {
        notification.setText(string);
    }

    private void restartBoard() {
        boardLogic = new BoardLogic(ROW, COL);
    }

    private void setButtons(boolean enabled) {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                symbolButtons[i][j].setEnabled(enabled);
                if (enabled) {
                    symbolButtons[i][j].setText("");
                }
            }
        }
    }

    private void startUpPanels() {
        JPanel center = new JPanel(new GridLayout(ROW, COL));
        Font font = new Font(BoardConstants.SYMBOL_FONT_TYPE,
                Font.BOLD,
                BoardConstants.SYMBOL_FONT_SIZE);
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                symbolButtons[i][j] = new JButton("");
                symbolButtons[i][j].setFont(font);
                symbolButtons[i][j].addActionListener(this);
                symbolButtons[i][j].setFocusable(false);
                center.add(symbolButtons[i][j]);
            }
        }

        startButton.addActionListener(this);

        JPanel north = new JPanel();
        north.add(notification);
        JPanel south = new JPanel();
        south.add(startButton);
        add(north, BoardConstants.UPPER_PANEL_NAME);
        add(center, BoardConstants.MIDDLE_PANEL_NAME);
        add(south, BoardConstants.BOTTOM_PANEL_NAME);

        setSize(BoardConstants.BOARD_SIZE, BoardConstants.BOARD_SIZE);
        setNotification(BoardConstants.STARTING_PLAY_MESSAGE);
        setButtons(false);
        setTitle(BoardConstants.BOARD_TITLE);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel boardPlay;
    // End of variables declaration//GEN-END:variables
}
