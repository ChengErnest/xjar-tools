package cn.nicollcheng.xjar;

import io.xjar.XKit;
import io.xjar.boot.XBoot;
import io.xjar.key.XKey;

/**
 * @author nicollcheng
 * <b>Creation Time:</b> 2021/9/10 13:59.
 */
public class XjarTools {
    public static void main(String[] args) throws Exception {
        if (args.length != 3) {
            StringBuilder stringBuilder = new StringBuilder("参数异常！请参考以下示例。").append("\n");
            stringBuilder.append("使用示例：").append("\n")
                    .append("\tjava -jar xjar-tools.jar D:/source.jar D:/dest.jar password").append("\n")
                    .append("\tD:/source.jar：待加密jar包").append("\n")
                    .append("\tD:/dest.jar：加密后的jar包").append("\n")
                    .append("\tpassword：加密或启动jar时用的密码");
            System.out.println(stringBuilder.toString());
        } else {
            String src = args[0];
            String dest = args[1];
            String password = args[2];
            //加密的密钥
            XKey xKey = XKit.key(password);
            //第1个参数是源文件，第2个参数是加密后的jar文件，第三个参数位加密密钥
            XBoot.encrypt(src, dest, xKey,
                    (entry) -> {
                        String name = entry.getName();
                        return !name.contains("static/");
                    });
        }
    }
}
