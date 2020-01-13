package app;

import dao.FileOperatorDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Window;
import task.DBInit;
import task.FileOperatorTask;
import task.FileScanCallback;
import task.FileScanTask;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private GridPane rootPane;

    @FXML
    private TextField searchField;

    @FXML
    private TableView<FileMeta> fileTable;

    @FXML
    private Label srcDirectory;

    private Thread t;

    public void initialize(URL location, ResourceBundle resources) {
        //数据库初始化
        DBInit.init();
        // 添加搜索框监听器，内容改变时执行监听事件
        searchField.textProperty().addListener(new ChangeListener<String>() {

            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                freshTable();
            }
        });
    }

    public void choose(Event event) {
        // 选择文件目录
        DirectoryChooser directoryChooser = new DirectoryChooser();
        Window window = rootPane.getScene().getWindow();
        File file = directoryChooser.showDialog(window);
        if (file == null)
            return;
        // 获取选择的目录路径，并显示
        String path = file.getPath();
        //给按钮后设置显示路径信息
        srcDirectory.setText(path);
        // TODO
        if (t != null) {
            t.interrupt();
        }
        t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    FileScanCallback callback = new FileOperatorTask();
                    //
                    FileScanTask task = new FileScanTask(callback);
                    task.startScan(file);
                    //多线程运行文件扫描任务
                    task.waitFinish();
                    System.out.println("执行完，src=" + srcDirectory.getText());
                    freshTable();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //等待task结束，刷新任务
                //task.wait();
            }
        });
        t.start();
        //System.out.println(path);
        //srcDirectory.setText(path);
    }

    // 刷新表格数据
    private void freshTable() {
        ObservableList<FileMeta> metas = fileTable.getItems();
        metas.clear();
        // TODO
        /*List<FileMeta> datats = new ArrayList<>();
        datats.add(new FileMeta("a", "D:/", 10496L, new Date().getTime(), true));
        datats.add(new FileMeta("b", "E:/", 1125698L, new Date().getTime(), true));
        datats.add(new FileMeta("c", "F:/", 14785236L, new Date().getTime(), true));*/
        List<FileMeta> datats = FileOperatorDAO.search(srcDirectory.getText(), searchField.getText());
        metas.addAll(datats);
    }
}