app.controller("JobController",function($scope,$http,$location,$log){
	$scope.job={"jobDescription":"","company":"","salary":"" ,"designation":"","location":""}
	$scope.jobData={}
	$scope.addJob=function(){
		console.log("Add job function")
		$http.post('http://localhost:8081/blogmiddleware/createJob',$scope.job).then(function(response){
			$scope.message = response.statusText
			console.log("Status Text:" + response.statusText)
			alert("Job added successfully")
		})
	}
	
	$scope.showJob=function(){
		console.log("Show all jobs")
		$http.get('http://localhost:8081/blogmiddleware/getAllJobs').then(function(response){
			$scope.jobData = response.data
			$log.info(response)
		},function(response){
			$scope.message = response.statusText.message
		}
		)
	}
	
})