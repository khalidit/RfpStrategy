var nodes = null;
var edges = null;
var network = null;	    
var data  = null;
var seed = 3;
var alist = JSON.parse('${actors}');
var elist = JSON.parse('${relations}');
//var rfp = JSON.parse('${rfp}');
var rfp_id ='${rfp_id}';

function changeDynamic() {
	console.log("changing dynamic");
console.log(network);
var physic = network.physics;
console.log(physic);
physic.physicsEnabled = !physic.physicsEnabled;
if(physic.physicsEnabled )
	$('#btnChangePhysic').html("DYNAMIC");
else
	$('#btnChangePhysic').html("FIXED");
}	
	
function destroy() {
   if (network !== null) {
     network.destroy();
     network = null;
   }
 }
		
	    
function draw() {

	destroy();			
	nodes = new vis.DataSet();				
	for(var i = 0; i < alist.length; i ++) {
		var node = alist[i];
		console.log(node);
		nodes.add({id:node["id"],label:node["name"],shape:node["type"], color:node["noteteamtrade"]});	
}						

edges = new vis.DataSet();     
for(var i = 0; i < elist.length; i ++) {
	var edge = elist[i];
	console.log(edge);			
	console.log(edge["arrows"]);
	edges.add({id:edge["id"],from:edge["from"],to:edge["to"],label:edge["label"],color:edge["color"], arrows:edge['arrows'],dashes:edge['dashes'],title:edge['title'],length:edge['length'],width:1/*edge['width']*/});	
}


data = {nodes: nodes,edges: edges};
// create a network
 	var container = document.getElementById('mynetwork');
 	var options = {    	
 		layout: {randomSeed:seed}, // just to make sure the layout is the same when the locale is changed
		interaction: {hover:true},    
  	 	manipulation: {
	     	addNode: function (data, callback) {	
			$('#operation').text("Add Person");	
	           document.getElementById('saveButtonNode').onclick = newNode.bind(this, data, callback);	            
	           document.getElementById('cancelButtonNode').onclick = clearPopUp.bind();
	           document.getElementById('node-popUp').style.display = 'block';
	        	},
	        editNode: function (data, callback) {
	          // filling in the popup DOM elements
	          	$('#operation').text("Edit Person");	      
	          	$('#node-label').val(data.label);
	          	$('#node-title').val(data.title);
	          	document.getElementById('node-type').value = data.shape;
	          	document.getElementById('node-color').value = data.color;            	
	          	document.getElementById('saveButtonNode').onclick = saveNode.bind(this, data, callback);
	          	document.getElementById('cancelButtonNode').onclick = cancelEdit.bind(this,callback);
	          	document.getElementById('node-popUp').style.display = 'block';
	        },
	        deleteNode:(function (data, callback) {
	      	  console.log(data["nodes"][0]);
	      	  $.ajax({
	      			url:' ${pageContext.request.contextPath}/rfp/'+rfp_id+"/deleteActor/"+data["nodes"][0],    			
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
	        
	        addEdge: function (data, callback) {
	          console.log("adding edges");
	          console.log(data);
			  document.getElementById('operation-edge').innerHTML = "Add Relation";         
			  document.getElementById('edge-label').value = (data.label == undefined ? "" : data.label ) ;
			  document.getElementById('edge-title').value = (data.title == undefined ? "" : data.title ) ;
	          document.getElementById('saveButtonEdge').onclick = newEdge.bind(this, data, callback);
	          document.getElementById('cancelButtonEdge').onclick = cancelEdit.bind(this,callback);
	          document.getElementById('edge-popUp').style.display = 'block';         
	          },
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
	          	document.getElementById('edge-popUp').style.display = 'block';
		
	        },
	        
	        deleteEdge: function(data,callback) {
	      	  console.log(data["edges"][0]);
	      	  $.ajax({
	      			url:' ${pageContext.request.contextPath}/rfp/'+rfp_id+"/deleteRelation/"+data["edges"][0],    			
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
     console.log(data);
 }
	
function clearPopUp() {
	document.getElementById('saveButtonNode').onclick = null;
document.getElementById('saveButtonEdge').onclick = null;
document.getElementById('cancelButtonNode').onclick = null;
document.getElementById('cancelButtonEdge').onclick = null;
document.getElementById('node-popUp').style.display = 'none';
document.getElementById('edge-popUp').style.display = 'none';
 }

function cancelEdit(callback) {
  clearPopUp();
  callback(null);
}

  function newNode(data,callback) {    
 		var actor = {
			name:$("#node-label").val(),
		title:$("#node-title").val(),
		type: $("#node-type").val(),
		noteteamtrade: $("#node-color").val()				
}	  		

  	$.ajax( {
  			url:' ${pageContext.request.contextPath}/rfp/'+rfp_id+"/newActor",    			
		data: JSON.stringify(actor),
		type :"POST",
		contentType: "application/json",
		success: function(response){
			var r = JSON.parse(response);
			console.log("Im here"+response);    	
		 	console.log(r);
	    	data.id =r["id"];
			data.label = r["name"];
			data.color = r["noteteamtrade"];
			data.shape = r["type"];
			data.title  = r["title"];		
  				console.log(data);
  		        clearPopUp();
  		        callback(data);
  				
  			}
  	});
 
  }
     
  function saveNode(nodeData,callback) {  
    	console.log("Ediging"+nodeData['id']);	
	var actor = {    		
		name: $("#node-label").val(),
		title:$("#node-title").val(),
		type: $("#node-type").val(),
		noteteamtrade: $("#node-color").val()
	}	
	
	$.ajax( {
		url:' ${pageContext.request.contextPath}/rfp/'+rfp_id+"/editActor/"+nodeData['id'],    			
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
			
	        clearPopUp();
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
			url:' ${pageContext.request.contextPath}/rfp/'+rfp_id+"/newRelation",    			
			data: JSON.stringify(relation),
			type :"POST",
			contentType: "application/json",
			success: function(response){    			
				console.log("Im here"+response); 	
				data.id = response;    				
				console.log(data);
		        clearPopUp();
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
		url:' ${pageContext.request.contextPath}/rfp/'+rfp_id+"/editRelation/"+data.id,    			
		data: JSON.stringify(relation),
		type :"POST",
		contentType: "application/json",
		success: function(response){    			
			console.log("Im here"+response); 	
			data.id = response;    				
			console.log(data);
	        clearPopUp();
	        callback(data);
			
		}
	});	
}