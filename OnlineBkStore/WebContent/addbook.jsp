<jsp:useBean  id="cart"  class="OnlineBkStore.Cart"  scope="session" />
<jsp:useBean  id="user"  class="OnlineBkStore.User"  scope="session" />

<%
  // check whether user has logged in, otherwise send user to login page
  out.println( user.isLogged());

  if (!user.isLogged())
   response.sendRedirect("login.html");

  // read data about item
  else
  {
  String  isbn = request.getParameter("isbn");

  cart.addItem(isbn);

  response.sendRedirect("home.jsp");
  }

%>

  