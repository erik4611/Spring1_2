angular.module('app', []).controller('productController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';

    $scope.itemsSum = 0;
    $scope.init = function () {
        $http.get(contextPath + '/api/v1/products')
            .then(function (response) {
                $scope.products = response.data;
                $scope.cartShow();
                $scope.loadPage = function (page) {
                    $http({
                        url: contextPath + "/api/v1/products",
                        method: "GET",
                        params: {
                            p: page
                        }
                    }).then(function (response) {
                        $scope.productsPage = response.data;

                    });

                    let minPageIndex = page - 2;
                    if (minPageIndex < 1) {
                        minPageIndex = 1;
                    }

                    let maxPageIndex = page + 2;
                    if (maxPageIndex > $scope.productsPage.totalPages) {
                        maxPageIndex = $scope.productsPage.totalPages;
                    }

                    $scope.paginationArray = $scope.generatePagesIndexes(minPageIndex, maxPageIndex);
                    console.log($scope.productsPage );
                    $scope.cartShow();

                };
    });


    $scope.createNewProduct = function () {
        console.log($scope.newProduct);
        $http.post(contextPath + '/api/v1/products', $scope.newProduct)
            .then(function successCallBack(response) {
                $scope.newProduct = null;
                $scope.init();
                $scope.loadPage($scope.productsPage.totalPages);
            }, function errorCallback(response) {
                console.log(response.data);
                alert("Error!!! \n" + response.data.messages);
            });
    };
    $scope.clickOnProduct = function (product) {
        console.log(product);
    }

    $scope.generatePagesIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    }

    /*
    * Cart functions
    * */
    $scope.sumItems = function () {
        $http.get(contextPath + '/api/v1/cart/summ')
            .then(function (response) {
                $scope.itemsSum = response.data;
            });
    };
    $scope.cartShow = function () {
        $http.get(contextPath + '/api/v1/cart')
            .then(function (response) {
                $scope.cart = response.data;
                $scope.sumItems();
            });
    }
    $scope.addProduct = function (productId) {
        $http({
            url: contextPath + "/api/v1/cart/add",
            method: "GET",
            params: {
                id: productId
            }
        }).then(function (response) {
            $scope.cartShow();
            $scope.itemsSum = response.data;
            console.log("OK");
        })
    }
    $scope.deleteItem = function (idProduct) {
        $http({
            url: contextPath + "/api/v1/cart/delete",
            method: "GET",
            params: {
                id: idProduct
            }
        }).then(function (response) {
            $scope.itemsSum = response.data;
            $scope.cartShow();
            console.log("OK");
        })
    }

    $scope.init();
    $scope.loadPage(1);
}
});