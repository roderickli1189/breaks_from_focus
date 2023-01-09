import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Stopwatch implements ActionListener {

    JFrame frame = new JFrame();
    JButton startButton = new JButton("START");
    JButton resetButton = new JButton("RESET");
    JLabel timeLabel = new JLabel();
    int userTime = 60000;
    int elapsedTime = 60000;
    int seconds = 0;
    int minutes = 1;
    int hours = 0;
    boolean started = false;
    String secondString = String.format("%02d", seconds);
    String minutesString = String.format("%02d", minutes);
    String hourString = String.format("%02d", hours);

    Timer timer = new Timer(1000, new ActionListener(){
        public void actionPerformed(ActionEvent e){
            
            elapsedTime = elapsedTime - 1000;
            hours = (elapsedTime/3600000);
            minutes = (elapsedTime/60000) % 60;
            seconds = (elapsedTime/1000) % 60;
            secondString = String.format("%02d", seconds);
            minutesString = String.format("%02d", minutes);
            hourString = String.format("%02d", hours);
            timeLabel.setText(hourString + ":" + minutesString + ":" + secondString);
        }
    });

    Stopwatch(){

        timeLabel.setText(hourString + ":" + minutesString + ":" + secondString);
        timeLabel.setBounds(100,100,200,100);
        timeLabel.setFont(new Font("Verdana", Font.PLAIN, 35));
        timeLabel.setBorder(BorderFactory.createBevelBorder(1));
        timeLabel.setOpaque(true);
        timeLabel.setHorizontalAlignment(JTextField.CENTER);
        
        startButton.setBounds(100, 200, 100, 50);
        startButton.setFont(new Font("Ink Free", Font.PLAIN, 20));
        startButton.setFocusable(false);
        startButton.addActionListener(this);

        resetButton.setBounds(200, 200, 100, 50);
        resetButton.setFont(new Font("Ink Free", Font.PLAIN, 20));
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);

        frame.add(startButton);
        frame.add(resetButton);
        frame.add(timeLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton){
            if(!started){
                started = true;
                startButton.setText("STOP");
                start();
            }
            else{
                started = false;
                startButton.setText("START");
                stop();
            }
        }
        if (e.getSource() == resetButton){
            started = false;
            startButton.setText("START");
            reset();
        }
        
    }

    void start(){
        timer.start();
    }

    void stop(){
        timer.stop();
    }

    void reset(){
        timer.stop();
        elapsedTime = userTime;
        hours = 0;
        minutes = 0;
        seconds = 0;
        secondString = String.format("%02d", seconds);
        minutesString = String.format("%02d", minutes);
        hourString = String.format("%02d", hours);
        timeLabel.setText(hourString + ":" + minutesString + ":" + secondString);
    }

    
}