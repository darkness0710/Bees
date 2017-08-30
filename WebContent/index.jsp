<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<%@ include file="includes/layouts/header.jsp" %>
	<%@ include file="includes/layouts/navbar.jsp" %>
	<body>
		<div class="container">
			<div id="page-content-wrapper">
            	<div class="container-fluid">
                	<div class="row">
                    	<div class="col-lg-12">
							<%@ include file="includes/layouts/left_navbar.jsp" %>
							<%@ include file="includes/bee/bee_table.jsp" %>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
	<%@ include file="includes/layouts/footer.jsp" %>
</html>