package baeshins.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BReplyViewCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String id = request.getParameter("id");
		BoardDao dao = new BoardDao();
		BoardDto dto = dao.reply_view(id);
		
		request.setAttribute("reply_view", dto);
		
	}

}
