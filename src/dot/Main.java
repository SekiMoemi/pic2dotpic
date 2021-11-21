package dot;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class Main
 */

@WebServlet("/dot/Main")
@MultipartConfig
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Main() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		Part part = request.getPart("file");
		String name = getFileName(part);
        Image result = null;
		try {
			BufferedImage bi = ImageIO.read(new File(name));
			String sq = request.getParameter("size");
			if(sq.equals("square")) {
				// 正方形での出力
				result = new SquareImage(bi);
			} else {
				// 長方形での出力
				result = new RectImage(bi);
			}

			// 処理の実行
			// ドットに変更
			result = new DotProcessing(result, 20);
			// 色の削減
			result = new ClassificationProcessing(result);
		} catch (Exception e) {
			System.out.println("name:"+name);
			e.printStackTrace();
			result = new SquareImage(new BufferedImage(200,200, BufferedImage.TYPE_INT_RGB));
		}
		// 画像の出力
		FileOutputStream out = null;
		try {
			System.out.println(name);
			request.setAttribute("name", name);
			name = name.replace(".png", "");
			name = name.replace(".PNG", "");
			name = name.replace(".jpg", "");
			name = name.replace(".JPG", "");

			out = new FileOutputStream(name + "_dot.png");
			request.setAttribute("result", name + "_dot.png");
			ImageIO.write(result.getImage(), "png", out);
			out.flush();
		} catch(Exception e) {
			e.printStackTrace();
		} finally{
            try {
                out.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
		// リダイレクト
        getServletContext().getRequestDispatcher("/dot/top.jsp").forward(request, response);
	}
	private String getFileName(Part part) {
		String[] split = part.getHeader("Content-Disposition").split(";");

		String fileName = null;
		for(String item: split) {
			if(item.trim().startsWith("filename")) {
				fileName = item.substring(item.indexOf('\"')).replaceAll("\"", "");
			}
		}
		return fileName;
	}
}