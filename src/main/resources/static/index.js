angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';

    $scope.init = function () {
        $http.get(contextPath + '/api/v1/products')
            .then(function (response) {
                $scope.products = response.data;
            });
    };

    $scope.createNewProduct = function () {
        $http.post(contextPath + '/api/v1/products', $scope.newProduct)
            .then(function successCallback(response) {
                $scope.init();
                $scope.newProduct = null;
            }, function errorCallback(response) {
                console.log(response.data);
                alert('Error: ' + response.data.messages);
            });
    };

    $scope.clickOnProduct = function (product) {
        console.log(product);
    }

    $scope.initCart = function () {
        $http.get(contextPath + '/api/v1/products')
            .then(function (response) {
                $scope.products = response.data;
            });
    };

    $scope.addProductInCart = function (productId) {
       $http.post(contextPath + '/api/v1/cart' + productId)
        .then(function (response) {
            $scope.initCart()

            console.log("OK");
        });
    };

    $scope.clearCart = function () {
        $http({
            url: contextPath + '/api/v1/cart/delete',
            method: 'DELETE'})
                .then(function (response) {
                $scope.initCart()

        });

    }

    $scope.pingProduct = function (productId) {
        $http({
            url: contextPath + '/api/v1/cart/ping',
            method: 'GET',
            params: {
                id: productId,
                temp: 'empty'
            }
        }).then(function (response) {
            console.log("OK");
        });
    }

    $scope.init();
});