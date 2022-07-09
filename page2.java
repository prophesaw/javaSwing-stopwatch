import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class page2 implements ChangeListener {
    JFrame frame = new JFrame("Countdown");
    JButton start = new JButton("START");
    JButton reset = new JButton("RESET");
    JButton change = new JButton("STOPWATCH");
    JLabel label = new JLabel();
    SpinnerNumberModel value = new SpinnerNumberModel(0, 0, 59, 1);
    SpinnerNumberModel value1 = new SpinnerNumberModel(0, 0, 59, 1);
    SpinnerNumberModel value2 = new SpinnerNumberModel(0, 0, 1000, 1);
    JSpinner psec = new JSpinner(value);
    JSpinner pmin = new JSpinner(value1);
    JSpinner phour = new JSpinner(value2);
    int sec = (int) value.getValue();
    int min = (int) value1.getValue();
    int hour = (int) value2.getValue();
    String Ssec = String.format("%02d", sec);
    String Smin = String.format("%02d", min);
    String Shour = String.format("%02d", hour);
    boolean state = false;
    int secount=0;
    int mincount = 0;
    int hourcount = 0;

    Timer counDown = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            secount--;
            if(secount==-1){
                secount=59;
                mincount-=1;
            }
            if(mincount==-1) {
                mincount = 59;
                hourcount-=1;
            }
            if((secount==0&&mincount==0)&&hourcount==0){
                psec.setEnabled(true);
                pmin.setEnabled(true);
                phour.setEnabled(true);
                value.setValue(0);
                value1.setValue(0);
                value2.setValue(0);
                counDown.restart();
                counDown.stop();

            }


            String tsec = String.format("%02d",secount);
            String tmin = String.format("%02d",mincount);
            String thour = String.format("%02d",hourcount);
            label.setText(thour+":"+tmin+":"+tsec);


        }
    });


    page2() {
        label.setText(Shour + ":" + Smin + ":" + Ssec);
        label.setBounds(100, 100, 200, 100);
        label.setHorizontalAlignment(JTextField.CENTER);
        label.setOpaque(true);
        label.setBorder(BorderFactory.createBevelBorder(1));
        label.setFont(new Font("Verdana", Font.PLAIN, 35));


        start.setBounds(100, 200, 100, 50);
        start.setFocusable(false);
        start.addActionListener(changer);

        reset.setBounds(200, 200, 100, 50);
        reset.setFocusable(false);
        reset.addActionListener(changer);


        psec.setBounds(270, 80, 30, 20);
        psec.addChangeListener(this);
        pmin.setBounds(180, 80, 30, 20);
        pmin.addChangeListener(this);
        phour.setBounds(100, 80, 30, 20);
        phour.addChangeListener(this);
        change.setBounds(1,1,110,20);
        change.addActionListener(changer);
        frame.add(change);


        frame.add(psec);
        frame.add(pmin);
        frame.add(phour);
        frame.add(start);
        frame.add(reset);
        frame.add(label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setVisible(true);
    }


    @Override
    public void stateChanged(ChangeEvent e) {
        int csec = (int) psec.getValue();
        for (int i = 0; i < csec; i++) {
            secount += 1;
        }
        int cmin = (int) pmin.getValue();
        for (int j = 0; j < cmin; j++) {
            mincount += 1;
        }
        int chour = (int) phour.getValue();
        for (int k = 0; k < chour; k++) {
            hourcount += 1;
        }


        do {
            if (csec == 59) {
                value.setValue(0);
                cmin += 1;
            }
        } while (cmin == 60);
        value1.setValue(cmin);

        do {
            if (cmin == 59) {
                value1.setValue(0);
                chour += 1;
            }
        } while (chour == 100);
        phour.setValue(chour);


        String s = String.format("%02d", csec);
        String m = String.format("%02d", cmin);
        String h = String.format("%02d", chour);

        label.setText(h + ":" + m + ":" + s);

    }

    ActionListener changer = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==start){
                if(state==false){
                    state=true;
                    start.setText("STOP");
                    counDown.start();
                    psec.setEnabled(false);
                    pmin.setEnabled(false);
                    phour.setEnabled(false);
                }else {
                    state = false;
                    start.setText("START");
                    counDown.stop();
                    psec.setEnabled(true);
                    pmin.setEnabled(true);
                    phour.setEnabled(true);
                }
                if ((hourcount==0&&mincount==0)&&secount==0){
                    start.setText("START");
                    reset();
                    psec.setEnabled(true);
                    pmin.setEnabled(true);
                    phour.setEnabled(true);
                }else{
                    start.setText("STOP");
                    psec.setEnabled(false);
                    pmin.setEnabled(false);
                    phour.setEnabled(false);
                }
            }
            if(e.getSource()==reset){
                reset();
            }
            if(e.getSource()==change){
                frame.dispose();
                newstop stop = new newstop();
            }

        }
    };
    void reset(){
        state = false;
        sec = 0;
        min = 0;
        hour = 0;

        String Ssec = String.format("%02d",sec);
        String Smin = String.format("%02d",min);
        String Shour = String.format("%02d",hour);
        label.setText(Shour+":"+Smin+":"+Ssec);
        psec.setEnabled(true);
        pmin.setEnabled(true);
        phour.setEnabled(true);
        counDown.restart();
        counDown.stop();
    }
}