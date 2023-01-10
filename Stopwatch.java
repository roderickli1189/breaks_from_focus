import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.*;

public class Stopwatch implements ActionListener, ChangeListener {

    JFrame frame = new JFrame();
    JButton startButton = new JButton("START");
    JButton resetButton = new JButton("RESET");
    JButton updateButton = new JButton("UPDATE");
    JLabel timeLabel = new JLabel();
    JSlider hourSlider = new JSlider(0,10, 0);
    JSlider minSlider = new JSlider(0,60, 0);
    JSlider secSlider = new JSlider(0,60, 0);
    JLabel hourSliderLabel = new JLabel();
    JLabel minSliderLabel = new JLabel();
    JLabel secSliderLabel = new JLabel();
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

        //hourSlider.setPreferredSize(new Dimension(400, 200));
        hourSlider.setBounds(15, 200, 380, 50);
        hourSlider.setPaintTrack(true);
        hourSlider.setMajorTickSpacing(1);
        hourSlider.setPaintLabels(true);
        hourSlider.setFont(new Font("MV Boli", Font.PLAIN, 15));
        hourSliderLabel.setText("number of hours: " + hourSlider.getValue());
        hourSliderLabel.setBounds(15,170,300,10);
        hourSliderLabel.setFont(new Font("Verdana", Font.PLAIN, 15));
        timeLabel.setOpaque(true);
        timeLabel.setHorizontalAlignment(JTextField.CENTER);

        //minSlider.setPreferredSize(new Dimension(400, 200));
        minSlider.setBounds(15, 250, 380, 50);
        
        //secSlider.setPreferredSize(new Dimension(400, 200));
        secSlider.setBounds(15, 300, 380, 50);
    
        timeLabel.setText(hourString + ":" + minutesString + ":" + secondString);
        timeLabel.setBounds(100,0,200,100);
        timeLabel.setFont(new Font("Verdana", Font.PLAIN, 35));
        timeLabel.setBorder(BorderFactory.createBevelBorder(1));
        timeLabel.setOpaque(true);
        timeLabel.setHorizontalAlignment(JTextField.CENTER);
        
        startButton.setBounds(100, 100, 100, 50);
        startButton.setFont(new Font("Ink Free", Font.PLAIN, 20));
        startButton.setFocusable(false);
        startButton.addActionListener(this);

        resetButton.setBounds(200, 100, 100, 50);
        resetButton.setFont(new Font("Ink Free", Font.PLAIN, 20));
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);

        frame.add(hourSliderLabel);
        frame.add(minSliderLabel);
        frame.add(secSliderLabel);
        frame.add(hourSlider);
        frame.add(minSlider);
        frame.add(secSlider);
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
        hours = (elapsedTime/3600000);
        minutes = (elapsedTime/60000) % 60;
        seconds = (elapsedTime/1000) % 60;
        secondString = String.format("%02d", seconds);
        minutesString = String.format("%02d", minutes);
        hourString = String.format("%02d", hours);
        timeLabel.setText(hourString + ":" + minutesString + ":" + secondString);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        // TODO Auto-generated method stub
        
    }

    
}