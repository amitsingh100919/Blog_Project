app.controller("notificationCtrl",function($scope,$rootScope,$location,$http){
	
		console.log("in notification")
		$http.get('http://localhost:8081/blogmiddleware/getNotify/' + id).then(function(response){
			$scope.notification = response.data
		},function(response){
			if(response.status==401)
				$location.url("/signin")
		}
		)
	
	/*$scope.updateNotification=function(id){
		
	}*/
})