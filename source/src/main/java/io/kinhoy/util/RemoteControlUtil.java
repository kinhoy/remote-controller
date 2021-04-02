package io.kinhoy.util;

import io.kinhoy.common.Constant;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * @author kinhoy
 * @Description 鼠标控制类
 * @Date 2020-11-29 00:43
 **/
@Slf4j
public class RemoteControlUtil {

    public static void doRemoteAction(String cmdType, int x, int y, String text) {
        try {
            Robot robot = new Robot();
            if (Constant.MOUSE_MOVE.equals(cmdType)) {
                Point point = MouseInfo.getPointerInfo().getLocation();

                float nowX = point.x;
                float nowY = point.y;

                robot.mouseMove((int) (nowX + x), (int) (nowY + y));
            } else if (Constant.MOUSE_LEFT.equals(cmdType)) {
                // 左键按下
                robot.mousePress(InputEvent.BUTTON1_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_MASK);
            } else if (Constant.MOUSE_RIGHT.equals(cmdType)) {
                // 右键按下
                robot.mousePress(InputEvent.BUTTON3_MASK);
                robot.mouseRelease(InputEvent.BUTTON3_MASK);
            } else if (Constant.KEYBOARD_IN.equals(cmdType)) {
                switch (text) {
                    case Constant.KEYBOARD_IN_TAB:
                        robot.keyPress(KeyEvent.VK_TAB);
                        robot.keyRelease(KeyEvent.VK_TAB);
                        break;
                    case Constant.KEYBOARD_IN_ENTER:
                        robot.keyPress(KeyEvent.VK_ENTER);
                        robot.keyRelease(KeyEvent.VK_ENTER);
                        break;
                    case Constant.KEYBOARD_IN_DEL:
                        robot.keyPress(KeyEvent.VK_BACK_SPACE);
                        robot.keyRelease(KeyEvent.VK_BACK_SPACE);
                        break;
                    case Constant.KEYBOARD_IN_SPACE:
                        robot.keyPress(KeyEvent.VK_SPACE);
                        robot.keyRelease(KeyEvent.VK_SPACE);
                        break;
                    case Constant.KEYBOARD_IN_SHIFT:
                        robot.keyPress(KeyEvent.VK_SHIFT);
                        robot.keyRelease(KeyEvent.VK_SHIFT);
                        break;
                    case Constant.KEYBOARD_IN_UP:
                        robot.keyPress(KeyEvent.VK_UP);
                        robot.keyRelease(KeyEvent.VK_SHIFT);
                        break;
                    case Constant.KEYBOARD_IN_DOWN:
                        robot.keyPress(KeyEvent.VK_DOWN);
                        robot.keyRelease(KeyEvent.VK_SHIFT);
                        break;
                    case Constant.KEYBOARD_IN_LEFT:
                        robot.keyPress(KeyEvent.VK_LEFT);
                        robot.keyRelease(KeyEvent.VK_LEFT);
                        break;
                    case Constant.KEYBOARD_IN_RIGHT:
                        robot.keyPress(KeyEvent.VK_RIGHT);
                        robot.keyRelease(KeyEvent.VK_RIGHT);
                        break;
                    case Constant.KEYBOARD_IN_CAPSLOCK:
                        robot.keyPress(KeyEvent.VK_CAPS_LOCK);
                        robot.keyRelease(KeyEvent.VK_CAPS_LOCK);
                        break;
                    case Constant.KEYBOARD_IN_ESC:
                        robot.keyPress(KeyEvent.VK_ESCAPE);
                        robot.keyRelease(KeyEvent.VK_ESCAPE);
                        break;
                    case "CMD":
                        robot.keyPress(KeyEvent.VK_WINDOWS);
                        robot.keyRelease(KeyEvent.VK_WINDOWS);
                        break;
                    case "+":
                        robot.keyPress(KeyEvent.VK_ADD);
                        robot.keyRelease(KeyEvent.VK_ADD);
                        break;
                    case "'":
                        robot.keyPress(KeyEvent.VK_QUOTE);
                        robot.keyRelease(KeyEvent.VK_QUOTE);
                        break;
                    case "\"":
                        robot.keyPress(KeyEvent.VK_QUOTEDBL);
                        robot.keyRelease(KeyEvent.VK_QUOTEDBL);
                        break;
                    default:
                        char[] c = text.toCharArray();
                        int cmd = (int) c[0];
                        log.info("远程键盘输入[{}] ASCII[{}]", cmdType, cmd);
                        robot.keyPress(cmd);
                        robot.keyRelease(cmd);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


