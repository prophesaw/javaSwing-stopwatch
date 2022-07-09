import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class newstop implements ActionListener {
    JFrame frame1 = new JFrame("Prophesaw's Stopwatch");
    JLabel label = new JLabel();
    JButton change = new JButton("COUNTDOWN");
    JButton startButton = new JButton("START");
    JButton resetButton = new JButton("RESET");

    boolean state = false;
    int starter = 0;
    int sec = 0;
    int min = 0;
    int hour = 0;
    String Ssec = String.format("%02d",sec);
    String Smin = String.format("%02d",min);
    String Shour = String.format("%02d",hour);








    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            starter += 1000;
            hour = (starter/3600000);
            min = (starter/60000)%60;
            sec = (starter/1000)%60;

            String Ssec = String.format("%02d",sec);
            String Smin = String.format("%02d",min);
            String Shour = String.format("%02d",hour);
            label.setText(Shour+":"+Smin+":"+Ssec);


        }
    });



    newstop(){
        label.setText(Shour+":"+Smin+":"+Ssec);
        label.setBounds(100,100,200,100);
        label.setHorizontalAlignment(JTextField.CENTER);
        label.setOpaque(true);
        label.setBorder(BorderFactory.createBevelBorder(1));
        label.setFont(new Font("Verdana",Font.PLAIN,35));


        startButton.setBounds(100,200,100,50);
        startButton.setFocusable(false);
        startButton.addActionListener(this);

        resetButton.setBounds(200,200,100,50);
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);



        change.setBounds(1,1,110,20);
        change.addActionListener(this);
        frame1.add(change);


        frame1.add(startButton);
        frame1.add(resetButton);
        frame1.add(label);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setSize(420,420);
        frame1.setLocationRelativeTo(null);
        frame1.setResizable(false);
        frame1.setLayout(null);
        frame1.setVisible(true);
    }





    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==startButton){
            if(state==false){
                state=true;
                startButton.setText("STOP");
                start();
            }else {
                state = false;
                startButton.setText("START");
                stop();
            }
        }
        if(e.getSource()==resetButton){
            reset();
        }
        if(e.getSource()==change){
            frame1.dispose();
            page2 page = new page2();
        }

    }


    void start(){

        timer.start();
    }
    void stop(){

        timer.stop();
    }
    void reset(){
        state = false;
        starter = 0;
        sec = 0;
        min = 0;
        hour = 0;

        String Ssec = String.format("%02d",sec);
        String Smin = String.format("%02d",min);
        String Shour = String.format("%02d",hour);
        label.setText(Shour+":"+Smin+":"+Ssec);
    }




}

