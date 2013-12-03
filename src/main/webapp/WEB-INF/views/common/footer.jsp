    </div>
    <footer>
      <p>&copy; Company 2013</p>
    </footer>
  </div> <!-- /container -->

</div>
  <!-- Bootstrap core JavaScript
  ================================================== -->
  <!-- Placed at the end of the document so the pages load faster -->

<c:if test="${not empty param.debug }" >  
  <b:debug title="PAGE" map="${pageScope }"/>
  <b:debug title="request" map="${requestScope }"/>
  <b:debug title="session" map="${sessionScope }"/>
  <b:debug title="application" map="${applicationScope }"/>
  <b:debug title="headers" map="${header }"/>
</c:if>  
</body>
</html>
