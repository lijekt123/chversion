package baeshins.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BWriteCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String name = request.getParameter("name");
		String pw = request.getParameter("pw");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		BoardDao dao = new BoardDao();
		dao.write(name, pw, title, content);
	}
}
