package jp.ossc.nimbus.service.test.swing;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import jp.ossc.nimbus.service.test.TestCase;
import jp.ossc.nimbus.service.test.TestController;
import jp.ossc.nimbus.service.test.TestCase.Status;

public class TestCaseListPanel extends JPanel /*implements ComponentListener*/ {
    
    private final int MARGIN = 8;       // ����A�s�Ԃ̗]��
    private final int HEIGHT = 30;      // �s�̍���
    
    private final int NO_WIDTH = 50;
    private int ID_WIDTH = 150; //��
    private int TITLE_WIDTH = 150; //��
    private final int START_DATE_WIDTH = 150;
    private final int END_DATE_WIDTH = 150;
    private final int STATE_WIDTH = 65;
    private final int START_BUTTON_WIDTH = 70;
    private final int END_BUTTON_WIDTH = 70;
    private final int DOWNLOAD_BUTTON_WIDTH = 90;
    
    private String userId = null;
    private TestController testController = null;
    private String scenarioGroupId = null;
    private String scenarioId = null;
    private List testCaseList = new ArrayList();

    private File cashDlDir = null;
    
    private List testCaseControlListenerList = null;
    
    private JFrame ownerFrame = null;
    
    private List lineList = null;
    
    public TestCaseListPanel(JFrame ownerFrame) throws Exception {
        this.ownerFrame = ownerFrame;
        this.initialize();
    }
    
    public void initialize() throws Exception {
        testCaseList = null;
        this.setupTestCaseCompornents();
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public void setTestController(TestController testController) {
        this.testController = testController;
    }
    
    public void setScenarioGroupId(String scenarioGroupId) {
        this.scenarioGroupId = scenarioGroupId;
    }
    
    public void setScenarioId(String scenarioId) {
        this.scenarioId = scenarioId;
    }
    
    public void setTestCaseList(List testCaseList) throws Exception {
        this.testCaseList = testCaseList;
        this.setupTestCaseCompornents();
        this.testCaseControlListenerList = new ArrayList();
    }
    
    public void resetup() throws Exception{
        this.setupTestCaseCompornents();
    }
    
    public void addTestCaseControlListener(TestCaseControlListener testCaseControlListener){
        this.testCaseControlListenerList.add(testCaseControlListener);
    }
    
    
    
    /**
     * �e�X�g�P�[�X�R���|�[�l���g�̃Z�b�g�A�b�v
     * @throws Exception 
     */
    private void setupTestCaseCompornents() throws Exception {

        int ROW_SPACE = 10;   // ��Ԃ̗]��
        Font font = new Font("�l�r �S�V�b�N", Font.BOLD, 16);
        
        int tmpX = 0;
        int tmpY = 0;
        
        String DATE_PATTERN = "yyyy/MM/dd HH:mm";
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
        
        // ������
        this.removeAll();
        this.setLayout(null);
        
        int winWidth = this.getWidth();
        
        ROW_SPACE = (winWidth - (NO_WIDTH + START_DATE_WIDTH + END_DATE_WIDTH + STATE_WIDTH + STATE_WIDTH + START_BUTTON_WIDTH + END_BUTTON_WIDTH + DOWNLOAD_BUTTON_WIDTH + MARGIN + MARGIN)) / 2;
        ID_WIDTH = ROW_SPACE;
        TITLE_WIDTH = ROW_SPACE;
        
        // �^�C�g���s�̃Z�b�g�A�b�v
        JLabel label1 = new JLabel("No");
        JLabel label2 = new JLabel("�e�X�g�P�[�XID");
        JLabel label3 = new JLabel("�e�X�g�P�[�X��");
        JLabel label4 = new JLabel("�J�n����");
        JLabel label5 = new JLabel("�I������");
        JLabel label6 = new JLabel("�װ���");
        JLabel label7 = new JLabel("���");
        JLabel label8 = new JLabel("�@");
        JLabel label9 = new JLabel("�@");
        JLabel label10 = new JLabel("�@");
        
        label1.setFont(font);
        label2.setFont(font);
        label3.setFont(font);
        label4.setFont(font);
        label5.setFont(font);
        label6.setFont(font);
        label7.setFont(font);
        label8.setFont(font);
        label9.setFont(font);
        label10.setFont(font);
        
        tmpX += MARGIN;
        tmpY += MARGIN;
        
        label1.setBounds(tmpX, tmpY, NO_WIDTH, HEIGHT);
        tmpX += NO_WIDTH;
        label2.setBounds(tmpX, tmpY, ID_WIDTH, HEIGHT);
        tmpX += ID_WIDTH;
        label3.setBounds(tmpX, tmpY, TITLE_WIDTH, HEIGHT);
        tmpX += TITLE_WIDTH;
        label4.setBounds(tmpX, tmpY, START_DATE_WIDTH, HEIGHT);
        tmpX += START_DATE_WIDTH;
        label5.setBounds(tmpX, tmpY, END_DATE_WIDTH, HEIGHT);
        tmpX += END_DATE_WIDTH;
        label6.setBounds(tmpX, tmpY, STATE_WIDTH, HEIGHT);
        tmpX += STATE_WIDTH;
        label7.setBounds(tmpX, tmpY, STATE_WIDTH, HEIGHT);
        tmpX += STATE_WIDTH;
        label8.setBounds(tmpX, tmpY, START_BUTTON_WIDTH, HEIGHT);
        tmpX += START_BUTTON_WIDTH;
        label9.setBounds(tmpX, tmpY, END_BUTTON_WIDTH, HEIGHT);
        tmpX += END_BUTTON_WIDTH;
        label10.setBounds(tmpX, tmpY, DOWNLOAD_BUTTON_WIDTH, HEIGHT);
        
        this.add(label1);
        this.add(label2);
        this.add(label3);
        this.add(label4);
        this.add(label5);
        this.add(label6);
        this.add(label7);
        this.add(label8);
        this.add(label9);
        
        if (testCaseList != null) {
            
            boolean startTestCaseFlg = false;
            
            lineList = new ArrayList();
            
            for (int i = 0; i < testCaseList.size(); i++) {
                
                tmpX = MARGIN;
                tmpY += HEIGHT + MARGIN;
                
                TestCase testCase = (TestCase) testCaseList.get(i);
                
                label1 = new JLabel((new Integer(i + 1)).toString());
                label2 = new JLabel(testCase.getTestCaseId());
                label2.setToolTipText(testCase.getTestCaseId());
                label3 = new JLabel(testCase.getTestCaseResource().getTitle());
                label3.setToolTipText(testCase.getTestCaseResource().getTitle());
                label4 = new JLabel("");
                label5 = new JLabel("");
                label6 = new JLabel("");
                
                TestErrorStatusDispButton tmpErrorStatusButton = new TestErrorStatusDispButton(this.ownerFrame);
                
                Status status = null;
                try {
                    status = testCase.getStatus();
                } catch (Exception e) {
                }
                
                label1.setFont(font);
                label2.setFont(font);
                label3.setFont(font);
                label4.setFont(font);
                label5.setFont(font);
                label6.setFont(font);

                Point xLinePoint = new Point(tmpX, tmpY - ((int)(MARGIN / 2)));
                
                label1.setBounds(tmpX, tmpY, NO_WIDTH, HEIGHT);
                tmpX += NO_WIDTH;
                label2.setBounds(tmpX, tmpY, ID_WIDTH, HEIGHT);
                tmpX += ID_WIDTH;
                label3.setBounds(tmpX, tmpY, TITLE_WIDTH, HEIGHT);
                tmpX += TITLE_WIDTH;
                label4.setBounds(tmpX, tmpY, START_DATE_WIDTH, HEIGHT);
                tmpX += START_DATE_WIDTH;
                label5.setBounds(tmpX, tmpY, END_DATE_WIDTH, HEIGHT);
                tmpX += END_DATE_WIDTH;
                tmpErrorStatusButton.setBounds(tmpX, tmpY, STATE_WIDTH, HEIGHT);
                tmpX += STATE_WIDTH;
                label6.setBounds(tmpX, tmpY, STATE_WIDTH, HEIGHT);
                tmpX += STATE_WIDTH;
                
                Point yLinePoint = new Point(tmpX, tmpY - ((int)(MARGIN / 2)));
                lineList.add(new Line(xLinePoint, yLinePoint));
                
                // �J�n���ԁA�I�����ԁA��� ��\��
                if (status != null){
                    if(status.getStartTime() != null)
                        label4.setText(dateFormat.format(status.getStartTime()));
                    if(status.getEndTime() != null)
                        label5.setText(dateFormat.format(status.getEndTime()));
                    
                    label6.setText(status.getStateString());
                }
                tmpErrorStatusButton.change(status);
                
                JButton tmpButton1 = new JButton("�J�n");
                tmpButton1.addActionListener(new StartButtonActionListener(testCase));
                
                JButton tmpButton2 = new JButton("�I��");
                tmpButton2.addActionListener(new EndButtonActionListener(testCase));
                
                JButton tmpButton3 = new JButton("����DL");
                tmpButton3.addActionListener(new DownloadButtonActionListener(testCase));
                
                if (status != null && status.getState() == TestCase.Status.STARTED) {
                    tmpButton1.setEnabled(false);
                    tmpButton2.setEnabled(true);
                    tmpButton3.setEnabled(false);
                    startTestCaseFlg = true;
                } else if (status != null && (status.getState() == TestCase.Status.ERROR || !status.getResult())) {
                    
                    try {
                        testController.cancelTestCase(scenarioId, testCase.getTestCaseId());
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    
                    tmpButton1.setEnabled(true);
                    tmpButton2.setEnabled(false);
                    tmpButton3.setEnabled(true);
                } else {
                    if(!startTestCaseFlg){
                        tmpButton1.setEnabled(true);
                    }else{
                        tmpButton1.setEnabled(false);
                    }
                    tmpButton2.setEnabled(false);
                    tmpButton3.setEnabled(false);
                }
                tmpButton1.setFont(font);
                tmpButton2.setFont(font);
                tmpButton3.setFont(font);
                
                tmpButton1.setBounds(tmpX, tmpY, START_BUTTON_WIDTH, HEIGHT);
                tmpX += START_BUTTON_WIDTH;
                tmpButton2.setBounds(tmpX, tmpY, END_BUTTON_WIDTH, HEIGHT);
                tmpX += END_BUTTON_WIDTH;
                tmpButton3.setBounds(tmpX, tmpY, DOWNLOAD_BUTTON_WIDTH, HEIGHT);

                this.add(label1);
                this.add(label2);
                this.add(label3);
                this.add(label4);
                this.add(label5);
                this.add(tmpErrorStatusButton);
                this.add(label6);
                this.add(tmpButton1);
                this.add(tmpButton2);
                this.add(tmpButton3);
            }
        }

        int tmpW = winWidth;
        int tmpH = tmpY + HEIGHT + MARGIN;

        this.setPreferredSize(new Dimension(tmpW, tmpH));
        this.repaint();
    }
    
    /**
     * �e�X�g�P�[�X�J�n�{�^���̃A�N�V�������X�i�[
     * @author j-higuchi
     */
    private class StartButtonActionListener implements ActionListener {
        
        private TestCase testCase = null;
        //private TestStatusDispButton errorStatusDispButton = null;
        
        public StartButtonActionListener(TestCase testCase) {
            this.testCase = testCase;
            //his.errorStatusDispButton = errorStatusDispButton;
        }

        public void actionPerformed(ActionEvent e) {
            // �e�X�g�P�[�X�̊J�n
            try {
                testController.startTestCase(userId, scenarioId, testCase.getTestCaseId());
                
                // �e�X�g�P�[�X���J�n�������ʁA�G���[�iResult��false�j�������ꍇ�A��ݾق𑗂�
                TestCase.Status state = testController.getTestCase(scenarioGroupId, scenarioId, testCase.getTestCaseId()).getStatus();
                if(!state.getResult()){
                    testController.cancelTestCase(scenarioId, testCase.getTestCaseId());
                }
                
                // ���X�i�[�Ăяo��
                for(int i=0; i < testCaseControlListenerList.size(); i++)
                    ((TestCaseControlListener)testCaseControlListenerList.get(i)).startTestCase(this.testCase);
                
            } catch (Exception e1) {
                
                try {
                    testController.cancelTestCase(scenarioId, testCase.getTestCaseId());
                } catch (Exception e2) {
                }
                JDialog dialog = new StatusDialogView(ownerFrame, "Exception", e1);
                dialog.setModal(true);
                dialog.setVisible(true);
            }
            
            // �R���|�[�l���g�̍Ĕz�u
            try {
                setupTestCaseCompornents();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * �e�X�g�P�[�X�I���{�^���̃A�N�V�������X�i�[
     * @author j-higuchi
     */
    private class EndButtonActionListener implements ActionListener {

        private TestCase testCase = null;
        //private TestStatusDispButton errorStatusDispButton = null;

        public EndButtonActionListener(TestCase testCase) {
            this.testCase = testCase;
            //this.errorStatusDispButton = errorStatusDispButton;
        }

        public void actionPerformed(ActionEvent e) {
            // �e�X�g�P�[�X�̏I��
            try {
                testController.endTestCase(scenarioId, testCase.getTestCaseId());
                
                // ���X�i�[�Ăяo��
                for(int i=0; i < testCaseControlListenerList.size(); i++)
                    ((TestCaseControlListener)testCaseControlListenerList.get(i)).endTestCase(this.testCase);
                
            } catch (Exception e1) {
                
                try {
                    testController.cancelTestCase(scenarioId, testCase.getTestCaseId());
                } catch (Exception e2) {
                }
                
                JDialog dialog = new StatusDialogView(ownerFrame, "Exception", e1);
                dialog.setModal(true);
                dialog.setVisible(true);
            }
            
            // �R���|�[�l���g�̍Ĕz�u
            try {
                setupTestCaseCompornents();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
    
    /**
     * �e�X�g�P�[�X�̎��s���ʃ_�E�����[�h�{�^���̃A�N�V�������X�i�[
     * @author j-higuchi
     */
    private class DownloadButtonActionListener implements ActionListener {
        
        private TestCase testCase = null;
        
        public DownloadButtonActionListener(TestCase testCase) {
            this.testCase = testCase;
        }
        
        public void actionPerformed(ActionEvent e) {
            // �e�X�g�P�[�X�̌��ʃ_�E�����[�h
            try {
                File dlDir = showDownloadFileSaveDialog(cashDlDir);
                if(dlDir != null){
                    testController.downloadTestCaseResult(dlDir, scenarioGroupId, scenarioId, testCase.getTestCaseId(), TestController.RESPONSE_FILE_TYPE_ZIP);
                    
                    showMessageDialog("�f�B���N�g���u" + dlDir + "�v��\r\n����Ƀ_�E�����[�h���������܂����B");
                }
                cashDlDir = dlDir;
                
                // �R���|�[�l���g�̍Ĕz�u
                setupTestCaseCompornents();
            } catch (Exception e1) {
                JDialog dialog = new StatusDialogView(ownerFrame, "Exception", e1);
                dialog.setModal(true);
                dialog.setVisible(true);
            }
        }
    }
    
    private File showDownloadFileSaveDialog(File cashDlDir){
        JFileChooser filechooser = new JFileChooser(cashDlDir);
        filechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int selected = filechooser.showSaveDialog(this);
        if (selected == JFileChooser.APPROVE_OPTION){
            return filechooser.getSelectedFile();
        }else{
            return null;
        }
    }
    
    private void showMessageDialog(String message){
        JOptionPane.showMessageDialog(this, message);
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        float dash[] = {1.0f, 1.0f};
        BasicStroke dsahStroke = new BasicStroke(1.0f, 
                BasicStroke.CAP_BUTT, 
                BasicStroke.JOIN_MITER, 
                1.0f,
                dash,
                0.0f);
        ((Graphics2D)g).setStroke(dsahStroke);
        
        if(lineList != null){
            for(int i = 0; i < lineList.size(); i++){
                Line tmpLine = (Line)lineList.get(i);
                if(tmpLine != null && tmpLine.getStartPoint() != null && tmpLine.getEndPoint() != null){
                    
                    g.drawLine(
                            (int)tmpLine.getStartPoint().getX(),
                            (int)tmpLine.getStartPoint().getY(),
                            (int)tmpLine.getEndPoint().getX(),
                            (int)tmpLine.getEndPoint().getY());
                }
            }
        }
    }
    
    
    private class Line{
        
        private Point startPoint = null;
        private Point endPoint = null;
        
        public Line(Point startPoint, Point endPoint){
            this.startPoint = startPoint;
            this.endPoint = endPoint;
        }

        public Point getStartPoint() {
            return startPoint;
        }
        public void setStartPoint(Point startPoint) {
            this.startPoint = startPoint;
        }

        public Point getEndPoint() {
            return endPoint;
        }
        public void setEndPoint(Point endPoint) {
            this.endPoint = endPoint;
        }
    }


}
