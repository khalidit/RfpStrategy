<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>RFP strategy application</title>

    <!-- Bootstrap Core CSS -->
    <link href="<c:url value='/resources/template/bower_components/bootstrap/dist/css/bootstrap.min.css' />" rel="stylesheet">
    <!-- Timeline CSS -->
    <link href="<c:url value='/resources/template/dist/css/timeline.css' />" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="<c:url value='/resources/template/dist/css/sb-admin-2.css' />" rel="stylesheet">
    <!-- Custom Fonts -->
    <link href="<c:url value='/resources/template/bower_components/font-awesome/css/font-awesome.min.css' />" rel="stylesheet" type="text/css">
	
	<link href='https://fonts.googleapis.com/css?family=Poiret+One|Josefin+Sans' rel='stylesheet' type='text/css'>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
    <div id="wrapper">
        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header"  style="padding:0.5em;">
				<i class="fa fa-group fa-3x"></i> <strong style="font-family: 'Josefin Sans', sans-serif; font-size:20px;">RFP Strategy </strong>
            </div>
            <!-- /.navbar-header -->
            <ul class="nav navbar-top-links navbar-right">
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i>Khalid ALIANNE <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a>
                        </li>
                        <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="login.html"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                        </li>
                    </ul>
                </li>
            </ul>

            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <li class="sidebar-search">
                            <div class="input-group custom-search-form">
                                <input type="text" class="form-control" placeholder="Search...">
                                <span class="input-group-btn">
                                <button class="btn btn-default" type="button">
                                    <i class="fa fa-search"></i>
                                </button>
                            </span>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
		<!-- End static content -->
		
		<!-- Block principal -->
		
        <div id="page-wrapper">
			 <br/>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-2 col-md-2">
                    <div class="panel panel-warning">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-group fa-3x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">${totalClients}</div>
                                    <div>Clients!</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-2 col-md-2">
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-gears fa-3x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div><span class="huge">${IDICATOR_1_VALUE}</span> RFPs</div>
                                    <div>${IDICATOR_1_NAME}!</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-2 col-md-2">
                    <div class="panel panel-yellow">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-hand-o-right fa-3x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div><span class="huge">${IDICATOR_2_VALUE}</span> RFPs</div>
                                    <div>${IDICATOR_2_NAME}!</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
				 <div class="col-lg-2 col-md-2">
                    <div class="panel panel-green">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-thumbs-o-up fa-3x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div><span class="huge">${IDICATOR_3_VALUE}</span> RFPs</div>
                                    <div>${IDICATOR_3_NAME}!</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
				 <div class="col-lg-2 col-md-2">
                    <div class="panel panel-red">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa  fa-thumbs-o-down fa-3x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div><span class="huge">${IDICATOR_4_VALUE}</span> RFPs</div>
                                    <div>${IDICATOR_4_NAME}!</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-10 col-md-8">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <i class="fa fa-group fa-fw"></i> All clients
                            <div class="pull-right">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown">
                                        Actions <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu pull-right" role="menu">
                                        <li ><a href="#"><i class="fa fa-user fa-fw"></i>Add new client</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
							<div class="panel-body">
								<div class="panel-group" id="accordion">
								 <c:forEach items="${clients}" var="client">
									<div class="panel panel-default">
										<div class="panel-heading">
											<h4 class="panel-title">
												<h4 data-toggle="collapse" data-parent="#accordion" href="#collapse${client.clientId}" style="cursor: pointer;">
													<i class="fa fa-angle-right fa-fw"></i> ${client.company.name} <span class="caret"></span>
													<span class="pull-right btn btn-info btn-circle">
														<strong style="font-size:8px;">${client.rfps.size()} RFP</strong>
													</span>
													<div class="pull-right" style="margin-right:1em;">
														<div class="btn-group">
															<button type="button" class="btn btn-primary btn-xs dropdown-toggle" data-toggle="dropdown">
																Actions
																<span class="caret"></span>
															</button>
															<ul class="dropdown-menu pull-right" role="menu">
																<li><a href="#"><i class="fa fa-plus-circle fa-fw"></i>Add new RFP</a></li>
															</ul>
														</div>
													</div>
												</h4>
											</h4>
										</div>
										<div id="collapse${client.clientId}" class="panel-collapse collapse">
											<div class="panel-body">
												<div class="list-group">
												<c:forEach items="${client.rfps}" var="rfp">
													<a href="rfp/${rfp.rfpId}" class="list-group-item panel-info">
														<i class="fa fa-check-square fa-fw"></i> ${rfp.name}
														<span class="pull-right text-muted small">${rfp.rfpStatus.name} :: <i class="fa fa-group fa-fw" style="color:green;"></i> <span style="color:green;"> ${rfp.actors.size()} actors</span> </span>
													</a>
												</c:forEach>
												</div>
											</div>
										</div>
									</div>
									</c:forEach>
								</div>
							</div>
						</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="<c:url value='/resources/template/bower_components/jquery/dist/jquery.min.js' />"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="<c:url value='/resources/template/bower_components/bootstrap/dist/js/bootstrap.min.js' />"></script>
    <!-- Custom Theme JavaScript -->
    <script src="<c:url value='/resources/template/dist/js/sb-admin-2.js' />" ></script>

</body>
</html>