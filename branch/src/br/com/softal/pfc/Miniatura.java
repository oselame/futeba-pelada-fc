package br.com.softal.pfc;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.ImageIcon;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class Miniatura {
	
	public static void converter(String imagesDir, String nmArquivo ) {
		
		int width = 640; // Lagura da miniatura
		int height = 444; // Altuta da miniatura
		int quality = 80; // Qualidade da imagem [0~100]
		File file = new File(imagesDir + nmArquivo);

		try {
			Image image = new ImageIcon(file.toURL()).getImage();
			redimensionar(image, width, height, quality, imagesDir, file.getName());

		} catch (MalformedURLException e) {
			System.out.println("MalformedURLException " + e.getMessage());
		}

	}

	// Método para redimensionar imagens (criar thubmnails)
	private static void redimensionar(Image image, int width, int height, int quality,
			String caminho, String nomeImagem) {

		// Calculos necessários para manter as propoçoes da imagem, conhecido
		// como "aspect ratio"
		double thumbRatio = (double) width / (double) height;
		int imageWidth = image.getWidth(null);
		int imageHeight = image.getHeight(null);

		double imageRatio = (double) imageWidth / (double) imageHeight;

		if (thumbRatio < imageRatio) {
			height = (int) (width / imageRatio);
		} else {
			width = (int) (height * imageRatio);
		}
		// Fim do cálculo

		BufferedImage thumbImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		Graphics2D graphics2D = thumbImage.createGraphics();

		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

		graphics2D.drawImage(image, 0, 0, width, height, null);

		BufferedOutputStream out;

		try {
			out = new BufferedOutputStream(new FileOutputStream(caminho + nomeImagem));
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(thumbImage);
			quality = Math.max(0, Math.min(quality, 100));
			param.setQuality((float) quality / 100.0f, false);
			encoder.setJPEGEncodeParam(param);
			encoder.encode(thumbImage);
			out.close();
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException " + e.getMessage());
		} catch (ImageFormatException e) {
			System.out.println("ImageFormatException " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IOException " + e.getMessage());
		}
	}

}

