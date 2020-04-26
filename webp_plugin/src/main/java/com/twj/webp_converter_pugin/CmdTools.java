package com.twj.webp_converter_pugin;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Description ：Cmd工具类
 * Created by Wenjing.Tang on 2020/4/4.
 */
public class CmdTools {

    public static final String TAG = CmdTools.class.getSimpleName();

    public static void cmd(String cmd, String params) {
        String cmdStr;
        if (isCmdExist(cmd)) {
            cmdStr = cmd + " " + params;
        } else {
            System.out.println(TAG + "isMac(): " + isMac() + "   getToolsDirPath():" + FileUtil.getToolsDirPath());
            if (isMac()) {
                cmdStr = FileUtil.getToolsDirPath() + "mac/" + cmd + " " + params;
            } else if (isLinux()) {
                cmdStr = FileUtil.getToolsDirPath() + "linux/" + cmd + " " + params;
            } else if (isWindows()) {
                cmdStr = FileUtil.getToolsDirPath() + "windows/" + cmd + " " + params;
            } else {
                cmdStr = "";
            }
        }
        if (cmdStr.equals("")) {
            LogUtil.log("McImage Not support this system");
            return;
        }
       // System.out.println(TAG + "#cmd()---  " + cmdStr);

        outputMessage(cmdStr);
    }

    public static boolean isLinux() {
        String system = System.getProperty("os.name");
        if (system == null) {
            return false;
        }
        return system.startsWith("Linux");
    }

    public static boolean isMac() {
        String system = System.getProperty("os.name");
        if (system == null) {
            return false;
        }
        return system.startsWith("Mac OS");
    }

    public static boolean isWindows() {
        String system = System.getProperty("os.name");
        if (system == null) {
            return false;
        }
        return system.toLowerCase().contains("win");
    }

    public static void chmod() {
        outputMessage("chmod 755 -R " + FileUtil.getRootDirPath());
    }

    private static void outputMessage(String cmd) {
        Process process;
        try {
            process = Runtime.getRuntime().exec(cmd);
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean isCmdExist(String cmd) {
        String result;
        if (isMac() || isLinux()) {
            result = executeCmd("which " + cmd);
        } else {
            result = executeCmd("where " + cmd);
        }
        return result != null && !result.isEmpty();
    }

    private static String executeCmd(String cmd) {
        Process process;
        try {
            process = Runtime.getRuntime().exec(cmd);
            process.waitFor();
            BufferedReader bufferReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            return bufferReader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
