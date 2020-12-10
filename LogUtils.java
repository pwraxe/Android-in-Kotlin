/**
Sometime long responce not display in logcat hence create 
new class and call this class when print output in logcat

Action : Convert this file in kotlin

*/


public class LogUtils {

    public static void e(String tag, String msg) {
        if (tag == null || tag.length() == 0
                || msg == null || msg.length() == 0)
            return;

        int segmentSize = 3 * 1024;
        long length = msg.length();
        if (length <= segmentSize ) {// length is less than or equal to limit print directly
            Log.e(tag, msg);
        }else {
            while(msg.length() > segmentSize ) {// Loop segment print log
                String logContent = msg.substring(0, segmentSize );
                msg = msg.replace(logContent, "");
                Log.e(tag, logContent);
            }
            Log.e(tag, msg);// Print remaining logs
        }
    }
}
