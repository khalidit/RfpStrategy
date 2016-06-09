<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
	<!-- Vis style -->
	<link href="<c:url value='/resources/vis/app.css' />" rel="stylesheet"></link>	
	<link href="https://cdnjs.cloudflare.com/ajax/libs/vis/4.14.0/vis.min.css" rel="stylesheet" type="text/css">
	<link href='https://fonts.googleapis.com/css?family=Poiret+One|Josefin+Sans' rel='stylesheet' type='text/css'>
	
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
  	<link rel="stylesheet" href="http://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    <script type="text/javascript">
    var nodes = null;
    var edges = null;
    var network = null;	    
    var data  = null;
    var seed = 3;
    var actorList = JSON.parse('${actors}');
    var relationList = JSON.parse('${relations}');
    var rfpId ='${rfpId}';
    
    function destroy() {
       if (network !== null) {
         network.destroy();
         network = null;
       }
     }
    		
    	    
    function drawRFP() {

    	destroy();			
    	nodesFA = new vis.DataSet();				
    	for(var i = 0; i < actorList.length; i ++) {
    		var node = actorList[i];
    		var actorType = node["actorType"]["name"];
    		var label = null;
    		var title = null;
    		if(actorType == "Company"){
    			label = node["name"];
    			title = '<div class="panel panel-default">';
    			title += ' <div class="panel-heading">';
    			title += '    <div class="row">';
    			title += '        <div class="col-xs-9 text-right">';
    			title += '        	  <div><img src="'+node['logo']+'" width="80px" height="80px" /></div>';
    			title += '            <div>';
    			title += '           		 <ul class="fa-ul">';
    			title += '      			  <li><i class="fa-li fa fa-check-square"></i>Appreciation : '+node['appreciation']['name']+'</li>';
    			title += '      			</ul>';
    			title += '             </div>';
    			title += '        </div>';
    			title += '    </div>';
    			title += ' </div>';
    			title += '</div>';
    		}else{
    			label = node["civility"]["name"]+" "+node["name"];
    			title = '<div class="panel panel-info">';
    			title += ' <div class="panel-heading">';
    			title += '    <div class="row">';
    			title += '        <div class="col-xs-9 text-left">';
    			
    			var avatar = node['avatar'];
    			if(avatar != "NA"){
    				title += '<div><img src="'+avatar+'" width="60px" height="60px" class="img-circle" alt="Profil" /></div>';
    			}
    			title += '            <div>';
    			title += '           		 <ul class="fa-ul">';
    			title += '      			  <li><i class="fa-li fa fa-check-square"></i>Appreciation : '+node['appreciation']['name']+'</li>';
    			title += '      			  <li><i class="fa-li fa fa-check-square"></i>Function : '+node['_function']+'</li>';
//     			title += '      			  <li><i class="fa-li fa fa-check-square"></i>Company : '+node['company']['name']+'</li>';
//     			title += '      			  <li><i class="fa-li fa fa-check-square"></i>Department : '+node['department']['name']+'</li>';
    			title += '      			</ul>';
    			title += '             </div>';
    			title += '        </div>';
    			title += '    </div>';
    			title += ' </div>';
    			title += '</div>';
    		}
    		nodesFA.add({id:node["actorId"], label:label, title: title, group: actorType});	
   		 }						
    	edges = new vis.DataSet();     
	    for(var i = 0; i < relationList.length; i ++) {
	    	var edge = relationList[i];
	    	edges.add({id:edge["id"],from:edge["from"],to:edge["to"],label:edge["label"],color:edge["color"], arrows:edge['arrows'],dashes:edge['dashes'],title:edge['title'],length:edge['length'],width:1/*edge['width']*/});	
	    }

    	data = {nodes: nodesFA,edges: edges};
   		
    	// create a network
     	var container = document.getElementById('mynetwork');
     	var options = {    	
     		layout: {randomSeed:seed}, // just to make sure the layout is the same when the locale is changed
     		groups: {
               Company: {
                 shape: 'icon',
                 icon: {
                 	face: 'FontAwesome',
                     code: '\uf1ad',
                     size: 50,
                     color: '#f0a30a'
                 }
               },
               Person: {
                 shape: 'icon',
                 icon: {
                   face: 'FontAwesome',
                   code: '\uf007',
                   size: 50,
                   color: '#aa00ff'
                 }
               }
            },
    		interaction: {hover:true},    
      	 	manipulation: {
    	     	addNode: function (data, callback) { 
    	     		$('#actorModal').modal();
    	     		document.getElementById('saveButtonActor').onclick = newActor.bind(this, data, callback);	
    	     	},	
    	        editNode: function (data, callback) {
    	          // filling in the popup DOM elements
    	          	$('#node-label').val(data.label);
    	          	$('#node-title').val(data.title);
    	          	document.getElementById('node-type').value = data.shape;
    	          	document.getElementById('node-color').value = data.color;            	
    	          	document.getElementById('saveButtonActor').onclick = editActor.bind(this, data, callback);
    	          	document.getElementById('cancelButtonActor').onclick = cancelEdit.bind(this,callback);
    	          	$('#actorModal').modal();
    	        },
    	        deleteNode:(function (data, callback) {
    	      	  console.log(data["nodes"][0]);
    	      	  $.ajax({
    	      			url:' ${pageContext.request.contextPath}/rfp/'+rfpId+"/deleteActor/"+data["nodes"][0],    			
    	      			data: null,
    	      			type :"GET",
    	      			contentType: "application/json",
    	      			success: function(response){
    	      				console.log(response);
    	      				callback(data);
    	      			}
    	      	  })
    	      	  callback(data);
    	        }),
    	        
    	        addEdge: function (data, callback) { $('#relationModal').modal();},
    	 	 	editEdge: function (data, callback) {
    	          	// filling in the popup DOM elements			
    	          	console.log("editing edge");	
    	          	console.log(data);
    	          	document.getElementById('operation-edge').innerHTML = "Edit Relation";
    	            document.getElementById('edge-label').value = "";
    	            document.getElementById('edge-title').value = "";
    	            document.getElementById('edge-length').value = "200";
    	            document.getElementById('edge-width').value = "1";					
    	         	document.getElementById('saveButtonEdge').onclick = saveEdge.bind(this, data, callback);
    	          	document.getElementById('cancelButtonEdge').onclick = cancelEdit.bind(this,callback);
    	          	//document.getElementById('edge-popUp').style.display = 'block';
    		
    	        },
    	        
    	        deleteEdge: function(data,callback) {
    	      	  console.log(data["edges"][0]);
    	      	  $.ajax({
    	      			url:' ${pageContext.request.contextPath}/rfp/'+rfpId+"/deleteRelation/"+data["edges"][0],    			
    	      			data: null,
    	      			type :"GET",
    	      			contentType: "application/json",
    		      			success: function(response){
    		      				console.log(response);
    		      				callback(data);
    		      			}
    		      	  })
    		      	  callback(data);
    		        }
         	  }
      	   };
      
         network = new vis.Network(container, data, options);
         network.physics.physicsEnabled = true;
     }
    	
    function cancelEdit(callback) {
      callback(null);
    }

    function newActor(data, callback) {
    	var actorType = $("#actor-type").val();
    	var actor = null;
    	
    	if(actorType == 2){ // Person case
    		actor = { actorType: {actorTypeId:actorType},
    				  actorRole: {roleId: $("#actor-role").val()},
 					  civility: {civilityId : $("#person-civility").val()},
 					  name: $("#person-firstname").val()+ " "+ $("#person-lastname").val(), 
 					  _function: $("#person-function").val(),
 					  avatar : $("#person-avatar").val(),
 					  manager: {actorId : $("#person-manager").val()}, 
 					  company: {actorId : $("#person-company").val()},
 					  department: {departmentId : $("#person-department").val()},
 					  appreciation: {appreciationId : $("#actor-appreciation").val()}
 				}; 
    	}else{ // Company case
    		actor = { actorType:{actorTypeId:actorType}, 
    				  actorRole: {roleId: $("#actor-role").val()},
					  name:$("#company-name").val(),
					  sirenNumber:$("#company-sirennumber").val(), 
					  logo:$("#company-logo").val(), 
					  appreciation: {appreciationId : $("#actor-appreciation").val()}
    				};
    	}
      	$.ajax({
      			url:' ${pageContext.request.contextPath}/rfp/'+rfpId+"/newActor",    			
    			data: JSON.stringify(actor),
    			type :"POST",
    			contentType: "application/json",
    			success: function(response){
	    			var r = JSON.parse(response);
	    	    	data.id =r["id"];
	    			data.label = r["name"];
	    			data.color = r["noteteamtrade"];
	    			data.shape = r["type"];
	    			data.title  = r["title"];		
      				$('#actorModal').modal('hide');
      		        callback(data);
      		     	 
	      		}
      		});
      }
         
      function editActor(nodeData,callback) {  
        	console.log("Ediging"+nodeData['id']);	
    	var actor = {    		
    		name: $("#node-label").val(),
    		title:$("#node-title").val(),
    		type: $("#node-type").val(),
    		noteteamtrade: $("#node-color").val()
    	}	
    	
    	$.ajax( {
    		url:' ${pageContext.request.contextPath}/rfp/'+rfpId+"/editActor/"+nodeData['id'],    			
    		data: JSON.stringify(actor),
    		type :"POST",
    		contentType: "application/json",
    		success: function(response){
    			console.log(response);
    			data.id = response;
    			data.label = $("#node-label").val();
    			data.color = $("#node-color").val();
    			data.shape = $("#node-type").val();
    			data.title = $("#node-title").val();		
    			
    	       // clearPopUp();
    	        callback(data);
    			
    		}
    	});
    }

    //Add new edge
    function newEdge(data,callback) {	
      data.label = document.getElementById('edge-label').value;
      data.title = document.getElementById('edge-title').value;
      data.length = document.getElementById('edge-length').value;	 
      data.color = document.getElementById('edge-color').value;
      data.width = document.getElementById('edge-width').value;	  
      switch(document.getElementById('edge-type').value) {
    		case "1": //Hierarchy:
    			data.dashes = true;
    			data.arrows = 'to';
    			data.color = 'gray';
    			break;
    		case "2": //Influence
    			data.arrows = 'to';				
    			break;
    		case '3': // Personal		
    			data.arrows = '';		
    			break;
    		default:
    			break;
      }
      
    //create relation object to send to server
    var relation = {
    			label:	data.label,
    			title:	data.title,
    			color: 	data.color,
    			arrows:	data.arrows,
    			dashes:	data.dashes,
    			length:	data.length,
    			width:	data.width,
    			from: data.from,
    			to: data.to
    }	  		
    	
    	$.ajax( {
    			url:' ${pageContext.request.contextPath}/rfp/'+rfpId+"/newRelation",    			
    			data: JSON.stringify(relation),
    			type :"POST",
    			contentType: "application/json",
    			success: function(response){    			
    				console.log("Im here"+response); 	
    				data.id = response;    				
    				console.log(data);
    		        callback(data);
    				
    			}
    	}); 	
     
    }

    //Save, modify edges	
    function saveEdge(data,callback) {
      //binding new data to vis edge
      data.label = document.getElementById('edge-label').value;
      data.title = document.getElementById('edge-title').value;
      data.length = document.getElementById('edge-length').value;	 
      data.width = document.getElementById('edge-width').value;	
      data.color = document.getElementById('edge-color').value;		
      switch(document.getElementById('edge-type').value) {
    		case "1": //Hierarchy:
    			data.dashes = true;
    			data.arrows = 'from';
    			data.color = 'gray';
    			break;
    		case "2": //Influence
    			data.arrows = 'to';				
    			break;
    		case '3': // Personal
    			data.arrows = '';		
    			break;
    		default:
    			break;
      }
      
      //create relation object to send to server
      var relation = {					
    				label:	data.label,
    				title:	data.title,
    				color: 	data.color,
    				arrows:	data.arrows,
    				dashes:	data.dashes,
    				length:	data.length,
    				width:	data.width,
    				from: data.from,
    				to: data.to
    	}	  		
      
     $.ajax( {
    		url:' ${pageContext.request.contextPath}/rfp/'+rfpId+"/editRelation/"+data.id,    			
    		data: JSON.stringify(relation),
    		type :"POST",
    		contentType: "application/json",
    		success: function(response){    			
    			console.log("Im here"+response); 	
    			data.id = response;    				
    			console.log(data);
    	        callback(data);
    			
    		}
    	});	
    }
    
    function setActorType(value){
		document.getElementById("actor-type").value= value;
	}
    </script>

</head>
<body onload="drawRFP();">
    <div id="wrapper">
        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header" style="padding:0.5em;">
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
		
        <div id="page-wrapper">
			 <br/>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-10 col-md-8">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                          <h5>  <i class="fa fa-home fa-1x"></i><a style="color:white;" href="${pageContext.request.contextPath}"> ${clientName} </a> <span class="fa fa-chevron-right fa-1x"></span> ${rfpName} details</h5>
                        </div>
                   		<div id="mynetwork">
							<div class="vis-network" touch-action: pan-y; -webkit-user-select: none; -webkit-user-drag: none; -webkit-tap-highlight-color: rgba(0, 0, 0, 0);">
								<canvas width="100%" height="100%" style="position: relative; touch-action: none; -webkit-user-select: none; -webkit-user-drag: none; -webkit-tap-highlight-color: rgba(0, 0, 0, 0); width: 100%; height: 100%;">
								</canvas>
								<div class="vis-manipulation" style="display: block;">
									<div class="vis-button vis-add" style="touch-action: pan-y; -webkit-user-select: none; -webkit-user-drag: none; -webkit-tap-highlight-color: rgba(0, 0, 0, 0);">
										<div class="vis-label">Add Actor</div>
									</div>
									<div class="vis-separator-line">
									</div>
									<div class="vis-button vis-connect" style="touch-action: pan-y; -webkit-user-select: none; -webkit-user-drag: none; -webkit-tap-highlight-color: rgba(0, 0, 0, 0);">
										<div class="vis-label">Add Relation</div>
									</div>
								</div>
								<div class="vis-edit-mode" style="display: block;"></div>
								<div class="vis-close" style="display: block; touch-action: pan-y; -webkit-user-select: none; -webkit-user-drag: none; -webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></div>
							</div>
						</div>
						
						<!-- Forms -->		
						<!-- Modal : add a new actor -->
						<div class="modal fade" id="actorModal" tabindex="-1" role="dialog" aria-labelledby="actorModalLabel">
						  <div class="modal-dialog" role="document">
						    <div class="modal-content">
						      <div class="modal-body">
						       <div class="panel panel-info">
		                         <div class="panel-heading">
		                            <div class="row">
							            <div class="col-lg-6">
							                <ul class="nav nav-tabs">
											  <li class="active"><a data-toggle="tab" href="#newPersonForm" onclick="setActorType(2);">Add Person</a></li>
											  <li><a data-toggle="tab" href="#newCompanyForm" onclick="setActorType(1);">Add Company</a></li>
											</ul>
											<div class="tab-content">
											<!-- Person tab -->
											   <div id="newPersonForm" class="tab-pane fade in active">
											   		<br>
											   		 <div class="form-group">
									                	<i class="fa fa-chevron-circle-right"></i>
									                    <label for="InputPersonCivility">Civility</label>
														 <div class="input-group">
															<select class="form-control" id="person-civility" name="InputPersonCivility" required>
															    <c:forEach items="${civilities}" var="civility">
															  	  <option value="${civility.civilityId}">${civility.name}</option> 
															    </c:forEach>
															</select>
									                      	<span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
									                      </div>
									                </div>
													<div class="form-group">
									                	<i class="fa fa-chevron-circle-right"></i>
									                    <label for="InputPersonFirstName">First Name</label>
									                    <div class="input-group">
									                        <input type="text" class="form-control" name="InputFirstName" id="person-firstname" placeholder="Enter first name" required>
									                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
									                    </div>
									                </div>
									                <div class="form-group">
									                	<i class="fa fa-chevron-circle-right"></i>
									                    <label for="InputPersonLastName">Last Name</label>
									                    <div class="input-group">
									                        <input type="text" class="form-control" name="InputLastName" id="person-lastname" placeholder="Enter last name" required>
									                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
									                    </div>
									                </div>
									                <div class="form-group">
									               		 <i class="fa fa-chevron-circle-right"></i>
									                    <label for="InputPersonFunction">Function</label>
									                    <div class="input-group">
									                        <input type="text" class="form-control" id="person-function" name="InputPersonFunction" placeholder="Enter Function" required>
									                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
									                    </div>
									                </div>
									                 <div class="form-group">
									                	<i class="fa fa-chevron-circle-right"></i>
									                    <label for="InputPersonManager">Manager</label>
														 <div class="input-group">
															<select class="form-control" id="person-manager" name="InputPersonManager" required>
															    <c:forEach items="${persons}" var="person">
															  	  <option value="${person.actorId}">${person.civility.name} ${person.name}</option> 
															    </c:forEach>
															</select>
									                      	<span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
									                      </div>
									                </div>
									                 <div class="form-group">
									                	<i class="fa fa-chevron-circle-right"></i>
									                    <label for="InputPersonCompany">Company</label>
														 <div class="input-group">
															<select class="form-control" id="person-company" name="InputPersonCompany" required>
															    <c:forEach items="${companies}" var="company">
															  	  <option value="${company.actorId}">${company.name}</option> 
															    </c:forEach>
															</select>
									                      	<span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
									                      </div>
									                </div>
									                 <div class="form-group">
									                	<i class="fa fa-chevron-circle-right"></i>
									                    <label for="InputPersonDepartment">Department</label>
														 <div class="input-group">
															<select class="form-control" id="person-department" name="InputPersonDepartment" required>
															    <c:forEach items="${departments}" var="department">
															  	  <option value="${department.departmentId}">${department.name}</option> 
															    </c:forEach>
															</select>
									                      	<span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
									                      </div>
									                </div>
									                <div class="form-group">
									               		 <i class="fa fa-chevron-circle-right"></i>
									                    <label for="InputPersonAvatar">Avatar</label>
									                    <div class="input-group">
									                        <input type="text" class="form-control" id="person-avatar" name="InputPersonAvatar" placeholder="Enter an avatar" required>
									                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
									                    </div>
									                </div>
												</div>
											<!-- Company tab -->
												 <div id="newCompanyForm" class="tab-pane fade" onclick="setActorType(1);">
												    <br>
													<div class="form-group">
									                	<i class="fa fa-chevron-circle-right"></i>
									                    <label for="InputCompanyName">Company name</label>
									                    <div class="input-group">
									                        <input type="text" class="form-control" name="InputCompanyName" id="company-name" placeholder="Enter company name" required>
									                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
									                    </div>
									                </div>
									                <div class="form-group">
									                	<i class="fa fa-chevron-circle-right"></i>
									                    <label for="InputSirenNumber">Siren Number</label>
									                    <div class="input-group">
									                        <input type="text" class="form-control" name="InputSirenNumber" id="company-sirennumber" placeholder="Enter the Siren Number" required>
									                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
									                    </div>
									                </div>
									                 <div class="form-group">
									                	<i class="fa fa-chevron-circle-right"></i>
									                    <label for="InputLogo">Logo</label>
									                    <div class="input-group">
									                        <input type="text" class="form-control" name="InputLogo" id="company-logo" placeholder="Enter the logo" required>
									                        <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
									                    </div>
									                </div>
												</div>
												<div class="form-group">
								                	<i class="fa fa-chevron-circle-right"></i>
								                    <label for="InputActorType">Role</label>
													 <div class="input-group">
														<select class="form-control" id = "actor-role" name="InputActorRole" required>
														    <c:forEach items="${actorRoles}" var="role">
														  	  <option value="${role.roleId}">${role.name}</option> 
														    </c:forEach>
														</select>
								                      	<span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
								                      </div>
								                </div>
								                <div class="form-group">
								                	<i class="fa fa-chevron-circle-right"></i>
								                    <label for="InputActorTTNote">TeamTrade Appreciation</label>
													 <div class="input-group">
														<select class="form-control" id="actor-appreciation" name="InputNodeColor" required>
														 	  <c:forEach items="${appreciations}" var="appreciation">
														  	 	 <option value="${appreciation.appreciationId}">${appreciation.name} (${appreciation.rating})</option> 
														      </c:forEach>
														</select>
								                      	<span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
								                      </div>
								               </div>
								               <div><input type="hidden" id="actor-type" value="2"></div>
									  </div>
							        </div>
					       		   </div>
					       	 	</div>
					       	 </div>
							</div>
						      <div class="modal-footer">
						        <button type="button" class="btn btn-default" data-dismiss="modal" id="cancelButtonActor">Cancel</button>
						        <button type="button" class="btn btn-info pull-right" id="saveButtonActor">Save changes</button>
						      </div>
						    </div>
						  </div>
						</div>
						
						<!-- Modal : add a new relation -->
						<div class="modal fade" id="relationModal" tabindex="-1" role="dialog" aria-labelledby="actorModalLabel">
						  <div class="modal-dialog" role="document">
						    <div class="modal-content">
						      <div class="modal-header">
						        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						        <h4 class="modal-title" id="actorModalLabel" id="operation-edge">Add a new relation between two actors</h4>
						      </div>
						      <div class="modal-body">
								<div class="panel panel-warning">
									<div class="panel-heading">
										<div class="row">
										  <form role="form">
											<div class="col-lg-6">
												<div class="well well-sm"><strong><span class="glyphicon glyphicon-asterisk"></span>Required Field</strong></div>
												<div class="form-group">
													<i class="fa fa-chevron-circle-right"></i>
													<label for="InputRelationLabel">Label</label>
													<div class="input-group">
														<input type="text" class="form-control" name="InputLabel" id="edge-label" placeholder="Enter relation label" required>
														<span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
													</div>
												</div>
												<div class="form-group">
													 <i class="fa fa-chevron-circle-right"></i>
													<label for="InputRelationLength">Length</label>
													<div class="input-group">
														<input type="text" class="form-control" id="edge-length" value="200" name="InputLength" placeholder="Enter the length" required>
														<span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
													</div>
												</div>
												<div class="form-group">
													 <i class="fa fa-chevron-circle-right"></i>
													<label for="InputRelationTitle">Title</label>
													<div class="input-group">
														<input type="text" class="form-control" id="edge-title" name="InputTitle" placeholder="Enter the title" required>
														<span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
													</div>
												</div>
												<div class="form-group">
													 <i class="fa fa-chevron-circle-right"></i>
													<label for="InputRelationWeight">Weight</label>
													<div class="input-group">
														<input type="text" class="form-control" id="edge-width" value="1" name="InputWeight" placeholder="Enter the weight" required>
														<span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
													</div>
												</div>				
												<div class="form-group">
													<i class="fa fa-chevron-circle-right"></i>
													<label for="InputRelationType">Relation Type</label>
													 <div class="input-group">
														<select class="form-control" id="edge-type" name="InputEdgeType" required>
															<c:forEach items="${relationTypes}" var="type">
															  <option value="${type.typeId}">${type.name}</option> 
															</c:forEach>
														</select>
														<span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
													  </div>
												</div>
												<div class="form-group">
													<i class="fa fa-chevron-circle-right"></i>
													<label for="InputRelationQuality">Relation quality</label>
													 <div class="input-group">
														<select class="form-control" id="edge-color" name="InputEdgeQuality" required>
															<c:forEach items="${relationQualities}" var="quality">
															  <option value="${quality.qualityId}">${quality.name}</option> 
															</c:forEach>
														</select>
														<span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
													  </div>
												</div>
											</div>
										   </form>
										</div>
									</div>
								 </div>	  
							  </div>
						      <div class="modal-footer">
						        <button type="button" class="btn btn-default" data-dismiss="modal" id="cancelButtonEdge">Cancel</button>
						        <button type="button" class="btn btn-info pull-right" id="saveButtonEdge">Save changes</button>
						      </div>
						    </div>
						  </div>
						</div>
					<!-- end forms -->
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
    
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/vis/4.14.0/vis.min.js"></script>

</body>
</html>