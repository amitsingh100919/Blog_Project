app.controller("BlogController",function($scope,$rootScope,$location,$http,$log){
	$scope.blog={"blogTitle":"","blogContent":""}
	$scope.getBlogs={}
	$scope.isRejected=false
	$scope.addBlog= function(){
		console.log("In add blog function")
		$http.post('http://localhost:8081/blogmiddleware/createBlog',$scope.blog).then(function(response){
			$scope.message=response.statusText
			alert("Blog added successfully")
		})
	}
	
	$scope.approvedBlogList = function() {
		console.log("Get All Approved Blogs Function");
		$scope.blogData = $http({
			method : 'GET',
			url : 'http://localhost:8081/blogmiddleware/getApproveBlog'
		}).then(function(response) {
			$scope.blogData = response.data;
			$log.info(response);
		}, function(reason) {
			$scope.message = reason.statusText.message;
		});
	}
	
	$scope.notApprovedBlogList = function(){
		console.log("Get all blogs waiting for approvel")
		$http.get('http://localhost:8081/blogmiddleware/getNotApproveBlog').then(function(response){
			$scope.waitingblogs = response.data
			$log.info(response)
		},function(response){
			$scope.message = response.statusText.message;
		}
		)
	}
	
	$scope.blogDetail=function(blogId)
	{
		console.log("In show blog detail function")
		$http.get('http://localhost:8081/blogmiddleware/getBlog/' + blogId).then(function(response){
			
			console.log(response.data)
			$scope.getBlogs=response.data;
			$rootScope.ob = $scope.getBlogs
			$log.info(response.data)
			$log.info($rootScope.user.role)
			$scope.message=response.statusText
			
			console.log("Status Text:" + response.statusText)
			if($rootScope.user.role=='U')
			$location.url("/showBlog")
			if($rootScope.user.role=='A')
			$location.url("/blogsdetail")	
		},function(response){
			$scope.message=response.statusText
		})
	}
	
	$scope.approveBlog = function(ob)
	{
		$http.put('http://localhost:8081/blogmiddleware/approveBlog',ob).then(function(response){
			$location.url('/blogswaitingforapproval')
		},function(response){
			$scope.message=response.statusText.message
			$location.url('/signin')
		}
		)
	}
	
	$scope.rejectBlog = function(ob,rejectionReason)
	{
		$http.put('http://localhost:8081/blogmiddleware/rejectBlog/'+rejectionReason,ob,$scope.rejectionReason).then(function(response){
			
			$location.url('/blogswaitingforapproval')
		},function(response){
			$scope.message=response.statusText.message
			$location.url('/signin')
		}
		)
	}
	$scope.showText = function()
	{
		$scope.isRejected =! $scope.isRejected
	}
	
	
	$scope.incLikes = function(blogId)
	{
		$http.get('http://localhost:8081/blogmiddleware/incrementLikes/'+blogId).then(function(response){
			console.log("Incrementing  likes in blog")
		})
	}
	
	
	$scope.incDislikes = function(blogId)
	{
		$http.get('http://localhost:8081/blogmiddleware/incrementDislikes/'+blogId).then(function(response){
			console.log("Decrementing likes in blog")
		})
	}
})