package baeshins.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BReplyCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		String id = request.getParameter("id");
	//	String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String group = request.getParameter("group");
		String step = request.getParameter("step");
		String indent = request.getParameter("indent");
		
		BoardDao dao = new BoardDao();
		dao.reply(id, name, title, content, group, step, indent);
		
	}

}
