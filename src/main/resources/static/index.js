const app = angular.module("MobileCatalogManagement", []);

app.controller("MobileCatalogController", function($scope, $http) {
    // let listDepartment = new Map(); // Инициализация списка подразделений
    // listDepartment.set("1","Отдел маркетинга");
    // listDepartment.set("2","Отдел сбыта");
    // listDepartment.set("3","Отдел кадров");
    // listDepartment.set("4","Экономический отдел");
    // listDepartment.set("5","Финансовый отдел");
    // listDepartment.set("6","Отдел снабжения");
    // listDepartment.set("7","Канцелярия");
    // listDepartment.set("8","Идеологический отдел");
    // let selestAllDepartments = document.getElementById("allDepartments");
    // let selestAllDepartmentsForFilter = document.getElementById("allDepartmentsForFilter");
    // for (const [key, value] of listDepartment) {    // Добавление списка подразделений в Select
    //     selestAllDepartments.options.add(new Option(value,key));
    //     selestAllDepartmentsForFilter.options.add(new Option(value,key));
    // }
    $scope.departments = [];
    $scope.department = {
        departmentId: null,
        nameDepartment: ""
    };
    $scope.catalogs = [];
    $scope.catalogForm = {
        id: null,
        fullName: "",
        position: "",
        myDepartment: $scope.department,
        serviceNumber: [],
        personalPhoneNumber: [],
        serviceMobilePhoneNumber: []
    };

    _refreshDepartments();
    _refreshMobileCatalogData();

    // get запрос на получение всех подразделений
    function _refreshDepartments() {
        $http({
            method: 'GET',
            url: '/departments'
        }).then(
            function(res) { // success
                $scope.departments = res.data;
                console.log(res.data);

            },
            function(res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }
    // get запрос на получение всех пользователей
    function _refreshMobileCatalogData() {
        $http({
            method: 'GET',
            url: '/mobileCatalog'
        }).then(
            function(res) { // success
                $scope.catalogs = res.data;
                console.log(res.data);
            },
            function(res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }

    // перенос данных с таблицы в форму для дальнейшего put-запроса
    $scope.editMobileCatalog = function(catalog) {
        $scope.catalogForm = catalog;
        document.getElementById("allDepartments").value=catalog.myDepartment.departmentId;
    };

    // put или post запрос
    $scope.submitMobileCatalog = function(mobileCatalog) {
        let method;
        let url;
        if ($scope.catalogForm.id == null) {
            method = "POST";
            url = '/mobileCatalog/';
            $scope.catalogForm.personalPhoneNumber = [$scope.catalogForm.personalPhoneNumber]
            $scope.catalogForm.serviceMobilePhoneNumber = [$scope.catalogForm.serviceMobilePhoneNumber]
            $scope.catalogForm.serviceNumber = [$scope.catalogForm.serviceNumber]
        } else {
            method = "PUT";
            url = '/mobileCatalog/' + mobileCatalog.id;
        }

        let list = document.getElementById("allDepartments")
        $scope.catalogForm.myDepartment = {departmentId : list.value, nameDepartment:$scope.departments[list.value-1].nameDepartment}
        $http({
                method: method,
                url: url,
                data: angular.toJson($scope.catalogForm),
                headers: {
                    'Content-Type': 'application/json'
                }
            }
        ).then(_success, _error);
    };

    //  Запрос о удалении
    $scope.deleteMobileCatalog = function(id) {
        $http({
            method: 'DELETE',
            url: '/mobileCatalog/' + id
        }).then(_success, _error);
    };
    // Очистка формы
    $scope.createMobileCatalog = function() {
        _clearFormData();
    }
    // Запрос о удалении списка пользователей
    $scope.deleteListMobileCatalog = function() {
        const arrayChecked = [];

        $('.array-checked:checked').each(function() {
            arrayChecked.push($(this).val());
        });
        console.log(arrayChecked);
        if(arrayChecked.length > 1){
            $http({
                method: 'DELETE',
                url: '/mobileCatalog/All',
                data: angular.toJson(arrayChecked),
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(_success, _error);
        }
        else if(arrayChecked.length === 1){$scope.deleteMobileCatalog(arrayChecked[0])}
        else {alert("Выберите поля")}
    };

    // Фильтр по подразделению
    $scope.myFilter = function (item){
        let allDepartmentsForFilter = document.getElementById("allDepartmentsForFilter");
        if (allDepartmentsForFilter.value==""){
            return item;
        } else {
            return item.myDepartment.departmentId == allDepartmentsForFilter.value;
        }
    }
    // Функция, выполняемая при успешном выполнении другой функции
    function _success() {
        _refreshMobileCatalogData();
        _clearFormData();
    }

    function _error(res) {
        const data = res.data;
        const status = res.status;
        alert("Error: " + status + ":" + data);
    }

    function _clearFormData() {
        $scope.catalogForm.id=null;
        $scope.catalogForm.fullName= "";
        $scope.catalogForm.position= "";
        $scope.catalogForm.serviceNumber= [];
        $scope.catalogForm.personalPhoneNumber= [];
        $scope.catalogForm.serviceMobilePhoneNumber= [];
    }
});
