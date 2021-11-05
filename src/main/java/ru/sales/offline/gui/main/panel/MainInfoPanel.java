package ru.sales.offline.gui.main.panel;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class MainInfoPanel extends JPanel {

    public MainInfoPanel() {

        setLayout(new MigLayout("debug, fillx", "[grow, right]"));

        JPanel sumInfoPanel = new JPanel();
        sumInfoPanel.add(new JLabel("Сумма к оплате наличными: "));
add(sumInfoPanel,"wrap");

        JPanel buttonPanel = new JPanel();
        JButton add = new JButton("Новый чек");
        add.setAlignmentX(Component.RIGHT_ALIGNMENT);
        add.addActionListener(e -> {});

        JButton move = new JButton("Оформить чек");
        move.setAlignmentX(Component.RIGHT_ALIGNMENT);
        move.addActionListener(e -> {});


        buttonPanel.add(add);
        buttonPanel.add(move);



        add(buttonPanel);

    }
}
