//package Question6;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.URL;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.io.*;
//import java.net.*;
//import java.nio.file.*;
//import java.util.concurrent.*;
//import java.util.concurrent.atomic.AtomicBoolean;
//
//
//
//public class huhu extends JFrame {
//    private JTextField urlField = new JTextField("");
//    private JButton addButton = new JButton("Add Download");
//    private DefaultListModel<DownloadInfo> listModel = new DefaultListModel<>();
//    private JList<DownloadInfo> downloadList = new JList<>(listModel);
//    private ExecutorService downloadExecutor = Executors.newFixedThreadPool(10); // 10 concurrent downloads
//
//    public huhu() {
//        super("Asynchronous Image Downloader");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setSize(1100, 600);
//        layoutComponents();
//        setVisible(true);
//        setLocationRelativeTo(null); // Center the JFrame on the screen
//    }
//
//    private void layoutComponents() {
//        // Set Nimbus Look and Feel for a modern look
//        try {
//            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        // Define colors and fonts
//        Font robotoBold24 = new Font("Roboto", Font.BOLD, 24);
//        Font robotoPlain18 = new Font("Roboto", Font.PLAIN, 18);
//        Color whiteColor = Color.WHITE;
//        Color darkGrayColor = new Color(64, 64, 64);
//        Color lightGrayColor = new Color(192, 192, 192);
//
//        // Set background color to dark gray for the content pane
//        getContentPane().setBackground(darkGrayColor);
//
//        // Create and customize components
////        JLabel titleLabel = new JLabel("Asynchronous Image Downloader");
////        titleLabel.setFont(robotoBold24);
////        titleLabel.setBackground(Color.black);
////        titleLabel.setForeground(Color.black);
////        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
//
//        JLabel titleLabel = new JLabel("Asynchronous Image Downloader");
//        titleLabel.setFont(new Font("Roboto", Font.BOLD, 24));
//        titleLabel.setForeground(whiteColor);
//        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
//
//
//        JPanel addPanel = new JPanel();
//        addPanel.setBackground(darkGrayColor);
//        addPanel.setLayout(new BorderLayout());
//
//        urlField.setFont(robotoPlain18);
//        urlField.setForeground(Color.black);
//        addButton.setFont(robotoPlain18);
//        addButton.setBackground(lightGrayColor);
//        addButton.setForeground(darkGrayColor);
//
//        addPanel.add(urlField, BorderLayout.CENTER);
//        addPanel.add(addButton, BorderLayout.EAST);
//
//        JScrollPane scrollPane = new JScrollPane(downloadList);
//        downloadList.setCellRenderer(new DownloadListRenderer());
//
//        JPanel buttonPanel = new JPanel();
//        buttonPanel.setBackground(darkGrayColor);
//
//        JButton pauseResumeButton = new JButton("Pause/Resume");
//        pauseResumeButton.setFont(robotoPlain18);
//        pauseResumeButton.setBackground(lightGrayColor);
//        pauseResumeButton.setForeground(darkGrayColor);
//
//        JButton cancelButton = new JButton("Cancel");
//        cancelButton.setFont(robotoPlain18);
//        cancelButton.setBackground(lightGrayColor);
//        cancelButton.setForeground(darkGrayColor);
//
//        // Add components to buttonPanel
//        buttonPanel.add(pauseResumeButton);
//        buttonPanel.add(cancelButton);
//
//        // Add action listeners
//        addButton.addActionListener(e -> addDownload(urlField.getText().trim()));
//
//        pauseResumeButton.addActionListener(e -> {
//            DownloadInfo selectedInfo = downloadList.getSelectedValue();
//            if (selectedInfo != null) {
//                selectedInfo.togglePauseResume();
//            }
//        });
//
//        cancelButton.addActionListener(e -> {
//            DownloadInfo selectedInfo = downloadList.getSelectedValue();
//            if (selectedInfo != null) {
//                selectedInfo.cancel();
//                listModel.removeElement(selectedInfo);
//            }
//        });
//
//        // Set layout and add components to the frame
//        setLayout(new BorderLayout());
//        add(addPanel, BorderLayout.NORTH);
//        add(titleLabel, BorderLayout.CENTER);
//        add(scrollPane, BorderLayout.CENTER);
//        add(buttonPanel, BorderLayout.SOUTH);
//    }
//
//
//    private void addDownload(String url) {
//        try {
//            new URL(url);
//            DownloadInfo info = new DownloadInfo(url);
//            listModel.addElement(info);
//            DownloadTask task = new DownloadTask(info, () -> SwingUtilities.invokeLater(this::repaint));
//            info.setFuture(downloadExecutor.submit(task));
//        } catch (MalformedURLException ex) {
//            JOptionPane.showMessageDialog(this, "Invalid URL: " + url, "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(huhu::new);
//    }
//}
//
//class DownloadInfo {
//    private final String url;
//    private volatile String status = "Waiting..."; // Corrected the typo here
//    private volatile long totalBytes = 0L;
//    private volatile long downloadedBytes = 0L;
//    private Future<?> future;
//    private final AtomicBoolean paused = new AtomicBoolean(false);
//
//    public DownloadInfo(String url) {
//        this.url = url;
//    }
//
//    public String getUrl() {
//        return url;
//    }
//
//    public synchronized boolean isPaused() {
//        return paused.get();
//    }
//
//    public synchronized void togglePauseResume() {
//        paused.set(!paused.get());
//        notifyAll();
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public synchronized void setStatus(String status) {
//        this.status = status;
//    }
//
//    public void setFuture(Future<?> future) {
//        this.future = future;
//    }
//
//    public void cancel() {
//        if (future != null)
//            future.cancel(true);
//    }
//
//    public synchronized void setTotalBytes(long totalBytes) {
//        this.totalBytes = totalBytes;
//    }
//
//    public synchronized void addDownloadedBytes(long bytes) {
//        this.downloadedBytes += bytes;
//    }
//
//    public long getDownloadedBytes() {
//        return downloadedBytes;
//    }
//
//    public long getTotalBytes() {
//        return totalBytes;
//    }
//}
//
//class DownloadTask implements Callable<Void> {
//    private final DownloadInfo info;
//    private final Runnable updateUI;
//
//    public DownloadTask(DownloadInfo info, Runnable updateUI) {
//        this.info = info;
//        this.updateUI = updateUI;
//    }
//
//    @Override
//    public Void call() throws Exception {
//        info.setStatus("Downloading");
//        @SuppressWarnings("deprecation")
//        URL url = new URL(info.getUrl());
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        long fileSize = connection.getContentLengthLong();
//        info.setTotalBytes(fileSize);
//
//        try (InputStream in = new BufferedInputStream(connection.getInputStream())) {
//            Path targetPath = Paths.get("downloads", new File(url.getPath()).getName());
//            Files.createDirectories(targetPath.getParent());
//            try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(targetPath))) {
//                byte[] buffer = new byte[1024];
//                int bytesRead;
//                while ((bytesRead = in.read(buffer)) != -1) {
//                    synchronized (info) {
//                        while (info.isPaused())
//                            info.wait();
//                    }
//                    out.write(buffer, 0, bytesRead);
//                    info.addDownloadedBytes(bytesRead);
//                    updateUI.run();
//                    Thread.sleep(200);
//                }
//                info.setStatus("Completed");
//            }
//        } catch (IOException | InterruptedException e) {
//            info.setStatus("Error: " + e.getMessage());
//            if (e instanceof InterruptedException) {
//                Thread.currentThread().interrupt();
//            }
//        } finally {
//            updateUI.run();
//        }
//        return null;
//    }
//}
//
//class DownloadListRenderer extends JPanel implements ListCellRenderer<DownloadInfo> {
//    @Override
//    public Component getListCellRendererComponent(JList<? extends DownloadInfo> list, DownloadInfo value, int index,
//                                                  boolean isSelected, boolean cellHasFocus) {
//        this.removeAll();
//        setLayout(new BorderLayout());
//        JLabel urlLabel = new JLabel(value.getUrl());
//        JProgressBar progressBar = new JProgressBar(0, 100);
//        if (value.getTotalBytes() > 0) {
//            int progress = (int) ((value.getDownloadedBytes() * 100) / value.getTotalBytes());
//            progressBar.setValue(progress);
//        }
//        JLabel statusLabel = new JLabel(value.getStatus());
//        add(urlLabel, BorderLayout.NORTH);
//        add(progressBar, BorderLayout.CENTER);
//        add(statusLabel, BorderLayout.SOUTH);
//
//        if (isSelected) {
//            setBackground(list.getSelectionBackground());
//            setForeground(list.getSelectionForeground());
//        } else {
//            setBackground(list.getBackground());
//            setForeground(list.getForeground());
//        }
//        return this;
//}
//}





package Question6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;



public class AsynchronousDownloader extends JFrame {
    private JTextField urlField = new JTextField("");
    private JButton addButton = new JButton("Add Download");
    private DefaultListModel<DownloadInfo> listModel = new DefaultListModel<>();
    private JList<DownloadInfo> downloadList = new JList<>(listModel);
    private ExecutorService downloadExecutor = Executors.newFixedThreadPool(10); // 10 concurrent downloads

    public AsynchronousDownloader() {
        super("Asynchronous Image Downloader");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 600);
        layoutComponents();
        setVisible(true);
        setLocationRelativeTo(null); // Center the JFrame on the screen
    }

    private void layoutComponents() {
        // Set Nimbus Look and Feel for a modern look
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Define colors and fonts
        Font robotoBold24 = new Font("Roboto", Font.BOLD, 24);
        Font robotoPlain18 = new Font("Roboto", Font.PLAIN, 18);
        Color whiteColor = Color.WHITE;
        Color darkGrayColor = new Color(64, 64, 64);
        Color lightGrayColor = new Color(192, 192, 192);

        // Set background color to dark gray for the content pane
        getContentPane().setBackground(darkGrayColor);

        // Create and customize components
        JLabel titleLabel = new JLabel("Asynchronous Image Downloader");
        titleLabel.setFont(robotoBold24);
        titleLabel.setForeground(whiteColor);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel addPanel = new JPanel();
        addPanel.setBackground(darkGrayColor);
        addPanel.setLayout(new BorderLayout());

        urlField.setFont(robotoPlain18);
        urlField.setForeground(Color.black);
        addButton.setFont(robotoPlain18);
        addButton.setBackground(lightGrayColor);
        addButton.setForeground(darkGrayColor);

        addPanel.add(urlField, BorderLayout.CENTER);
        addPanel.add(addButton, BorderLayout.EAST);

        JScrollPane scrollPane = new JScrollPane(downloadList);
        downloadList.setCellRenderer(new DownloadListRenderer());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(darkGrayColor);

        JButton pauseResumeButton = new JButton("Pause/Resume");
        pauseResumeButton.setFont(robotoPlain18);
        pauseResumeButton.setBackground(lightGrayColor);
        pauseResumeButton.setForeground(darkGrayColor);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(robotoPlain18);
        cancelButton.setBackground(lightGrayColor);
        cancelButton.setForeground(darkGrayColor);

        // Add components to buttonPanel
        buttonPanel.add(pauseResumeButton);
        buttonPanel.add(cancelButton);

        // Add action listeners
        addButton.addActionListener(e -> addDownload(urlField.getText().trim()));

        pauseResumeButton.addActionListener(e -> {
            DownloadInfo selectedInfo = downloadList.getSelectedValue();
            if (selectedInfo != null) {
                selectedInfo.togglePauseResume();
            }
        });

        cancelButton.addActionListener(e -> {
            DownloadInfo selectedInfo = downloadList.getSelectedValue();
            if (selectedInfo != null) {
                selectedInfo.cancel();
                listModel.removeElement(selectedInfo);
            }
        });

        // Set layout and add components to the frame
        setLayout(new BorderLayout());
        add(addPanel, BorderLayout.NORTH);
        add(titleLabel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }


    private void addDownload(String url) {
        try {
            new URL(url);
            DownloadInfo info = new DownloadInfo(url);
            listModel.addElement(info);
            DownloadTask task = new DownloadTask(info, () -> SwingUtilities.invokeLater(this::repaint));
            info.setFuture(downloadExecutor.submit(task));
        } catch (MalformedURLException ex) {
            JOptionPane.showMessageDialog(this, "Invalid URL: " + url, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AsynchronousDownloader::new);
    }
}

class DownloadInfo {
    private final String url;
    private volatile String status = "Waiting..."; // Corrected the typo here
    private volatile long totalBytes = 0L;
    private volatile long downloadedBytes = 0L;
    private Future<?> future;
    private final AtomicBoolean paused = new AtomicBoolean(false);

    public DownloadInfo(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public synchronized boolean isPaused() {
        return paused.get();
    }

    public synchronized void togglePauseResume() {
        paused.set(!paused.get());
        notifyAll();
    }

    public String getStatus() {
        return status;
    }

    public synchronized void setStatus(String status) {
        this.status = status;
    }

    public void setFuture(Future<?> future) {
        this.future = future;
    }

    public void cancel() {
        if (future != null)
            future.cancel(true);
    }

    public synchronized void setTotalBytes(long totalBytes) {
        this.totalBytes = totalBytes;
    }

    public synchronized void addDownloadedBytes(long bytes) {
        this.downloadedBytes += bytes;
    }

    public long getDownloadedBytes() {
        return downloadedBytes;
    }

    public long getTotalBytes() {
        return totalBytes;
    }
}

class DownloadTask implements Callable<Void> {
    private final DownloadInfo info;
    private final Runnable updateUI;

    public DownloadTask(DownloadInfo info, Runnable updateUI) {
        this.info = info;
        this.updateUI = updateUI;
    }

    @Override
    public Void call() throws Exception {
        info.setStatus("Downloading");
        @SuppressWarnings("deprecation")
        URL url = new URL(info.getUrl());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        long fileSize = connection.getContentLengthLong();
        info.setTotalBytes(fileSize);

        try (InputStream in = new BufferedInputStream(connection.getInputStream())) {
            Path targetPath = Paths.get("downloads", new File(url.getPath()).getName());
            Files.createDirectories(targetPath.getParent());
            try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(targetPath))) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    synchronized (info) {
                        while (info.isPaused())
                            info.wait();
                    }
                    out.write(buffer, 0, bytesRead);
                    info.addDownloadedBytes(bytesRead);
                    updateUI.run();
                    Thread.sleep(200);
                }
                info.setStatus("Completed");
            }
        } catch (IOException | InterruptedException e) {
            info.setStatus("Error: " + e.getMessage());
            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
        } finally {
            updateUI.run();
        }
        return null;
    }
}

class DownloadListRenderer extends JPanel implements ListCellRenderer<DownloadInfo> {
    @Override
    public Component getListCellRendererComponent(JList<? extends DownloadInfo> list, DownloadInfo value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
        this.removeAll();
        setLayout(new BorderLayout());
        JLabel urlLabel = new JLabel(value.getUrl());
        JProgressBar progressBar = new JProgressBar(0, 100);
        if (value.getTotalBytes() > 0) {
            int progress = (int) ((value.getDownloadedBytes() * 100) / value.getTotalBytes());
            progressBar.setValue(progress);
        }
        JLabel statusLabel = new JLabel(value.getStatus());
        add(urlLabel, BorderLayout.NORTH);
        add(progressBar, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        return this;}
}