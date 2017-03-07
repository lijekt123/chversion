package baeshins.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BContentCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		String id = request.getParameter("id");
		BoardDao dao = new BoardDao();
		BoardDto dto = dao.contentView(id);
		
		request.setAttribute("content_view", dto);
		
	}

}
