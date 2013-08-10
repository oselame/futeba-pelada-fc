package br.com.softal.pfc.util;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageUtil {

	/**
	 * Converte uma imagem e um array de bytes
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static byte[] readImageAsByteArray(String filename) throws IOException {
		byte[] buffer = new byte[1024];
		//InputStream is = this.getClass().getResourceAsStream(filename);
		InputStream is = new FileInputStream(filename);
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		while (is.read(buffer) != -1) {
			out.write(buffer);
		}
		return out.toByteArray();
	}
	
}
