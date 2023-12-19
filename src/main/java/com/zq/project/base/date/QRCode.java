package com.zq.project.base.date;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 生成二维码
 */
public class QRCode {

    public static void main(String[] args) throws WriterException, IOException {
        // 二维码内容
        String content = "https://www.baidu.com";
        // 二维码图片宽度
        int width = 300;
        // 二维码图片高度
        int height = 300;
        // 二维码生成的格式
        String format = "png";
        // 二维码的边距
        int margin = 0;
        // 生成二维码
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height,
                new HashMap<EncodeHintType, Object>() {
                    {
                        put(EncodeHintType.CHARACTER_SET, "UTF-8");
                        put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
                    }
                });
        // 生成二维码图片
        BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
        // 将二维码图片保存到本地
        ImageIO.write(bufferedImage, format, new File("D:/qrcode.png"));
    }
}