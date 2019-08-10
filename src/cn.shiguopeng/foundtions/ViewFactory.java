package cn.shiguopeng.foundtions;

import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.lang.reflect.Field;
import java.util.logging.Logger;

public abstract class ViewFactory {

    protected Stage stage;

    public ViewFactory(Stage stage, ControllerFactory controllerFactory) {

        // 全局设置
        stage.getIcons().add(new Image("/resources/icon.png"));
        stage.setTitle("通讯录");
        stage.setWidth(600);
        stage.setHeight(400);

        this.stage = stage;

        try {

            // 构造控制器
            Field controllerField = this.getClass().getDeclaredField("controller");
            controllerField.setAccessible(true);
            controllerField.set(this, controllerFactory);

        } catch (NoSuchFieldException e) {

            Logger.getGlobal().info(this.getClass().getName() + "视图没有控制器");
        } catch (IllegalAccessException | IllegalArgumentException e) {

            Logger.getGlobal().info(this.getClass().getName() + "视图无法设置控制器");
        }

        make();
    }

    public abstract void make();
}
