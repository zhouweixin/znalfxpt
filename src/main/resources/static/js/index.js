var urlFindProcessRoot = "/znalfxpt/process/findProcessRoot";
var urlAddProcess = "/znalfxpt/process/add";
var urlDeleteById = "/znalfxpt/process/deleteById";
var urlDeleteByIdWithSon = "/znalfxpt/process/deleteByIdWithSon";

function deleteById(id) {
    $.ajax({
        url: urlDeleteById,
        data: {id: id},
        type: 'get',
        cache: false,
        dataType: 'json',
        success: function (result) {
            if (result.code != 0) {
                alert(result.message);
                return;
            }

            alert("删除完成");
        },
        error: function () {
            console.log("异常！");
        }
    });
}

function deleteByIdWithSon(id) {
    $.ajax({
        url: urlDeleteByIdWithSon,
        data: {id: id},
        type: 'get',
        cache: false,
        dataType: 'json',
        success: function (result) {
            if (result.code != 0) {
                alert(result.message);
                return;
            }

            alert("级联删除完成");
        },
        error: function () {
            console.log("异常！");
        }
    });
}

function addProcess(data) {
    $.ajax({
        url: urlAddProcess,
        data: data,
        type: 'post',
        cache: false,
        dataType: 'json',
        success: function (result) {
            if (result.code != 0) {
                alert(result.message);
                return;
            }
            findProcessRoot();

            alert("新增完成");
        },
        error: function () {
            console.log("异常！");
        }
    });
}

function findProcessRoot() {
    $.ajax({
        url: urlFindProcessRoot,
        data: {},
        type: 'get',
        cache: false,
        dataType: 'json',
        success: function (result) {
            if (result.code != 0) {
                alert(result.message);
                return;
            }

            renderProcess(result.data);
        },
        error: function () {
            console.log("异常！");
        }
    });
}

// 1.从后端请求数据

// 2.处理数据
// 定义函数
function parseData(data, parentId) {
    if (data == null) {
        return data;
    }

    data.value = data.id;
    data.questionType = data.questionType
    data.userType = data.userType
    data.parentId = parentId

    data.name1 = data.name;
    data.name = data.content;
    data.children = data.processes;
    data.processes = null;

    sonIds = []
    for (var i = 0; i < data.children.length; i++) {
        sonIds.push(data.children[i].id);
    }
    data.sonId = sonIds;

    for (var i = 0; i < data.children.length; i++) {
        data.children[i] = parseData(data.children[i], data.id)
    }
    return data
}

// 3.调用Echars呈现
function renderProcess(data) {
    data = parseData(data);
    data = [data];

    require.config({
        paths: {
            echarts: 'js/echarts-dist'
        }
    });

    require(
        ['echarts', 'echarts/chart/tree'],
        function (ec) {
            var myChart = ec.init(document.getElementById("main"));
            // 修改的代码
            // 开始
            option = {
                title: {
                    text: '树图',
                    subtext: '真实数据'
                },
                toolbox: {
                    show: true,
                    feature: {
                        mark: {show: true},
                        dataView: {show: true, readOnly: false},
                        restore: {show: true},
                        saveAsImage: {show: true}
                    }
                },
                calculable: true,
                series: [
                    {
                        name: '对话流程图',
                        type: 'tree',
                        orient: 'horizontal',  // vertical horizontal
                        rootLocation: {x: 100, y: 'center'}, // 根节点位置  {x: 100, y: 'center'}
                        nodePadding: 50,
                        itemStyle: {
                            normal: {
                                label: {
                                    show: true,
                                    formatter: "{b}"
                                },
                                lineStyle: {
                                    color: '#48b',
                                    shadowColor: '#000',
                                    shadowBlur: 3,
                                    shadowOffsetX: 0,
                                    shadowOffsetY: 0,
                                    type: 'broken' // 'curve'|'broken'|'solid'|'dotted'|'dashed'

                                }
                            },
                            emphasis: {
                                label: {
                                    show: true
                                }
                            }
                        },

                        data: data
                    }
                ]
            };
            // 结束

            myChart.setOption(option);
            myChart.on("click", function (node) {
                console.log(node);
                node = node.data

                $("#node-id").val(node.id);
                $("#node-content").val(node.content || '');
                $("#node-user-type").val(node.userType);
                $("#node-question-type").val(node.questionType);
                $("#node-parent-id").val(node.parentId || '');

                $("#node-son-id").empty();
                $("#node-son-id").append("<option id='-1'>无</option>");
                for(var i=0; i<node.children.length; i++) {
                    n = node.children[i];
                    $("#node-son-id").append("<option id='" + n.id + "'>"+n.content+"</option>");
                }
            });
        }
    );
}

function submit() {
    var node = {}

    node.id = $("#node-id").val();
    node.content = $("#node-content").val();
    node.userType = $("#node-user-type").val();
    node.questionType = $("#node-question-type").val();
    node.parentId = $("#node-parent-id").val();
    node.sonId = $("#node-son-id").find("option:selected").attr("id");

    console.log(node);
    addProcess(node);
}

function add() {
    var data = {
        name: "测试用",
        content: "测试用",
        userType: 0,
        questionType: 0,
        parentId: 1,
        sonId: 2
    }
    addProcess(data);
}

function delete1() {
    deleteById(18);
}

function delete2() {
    deleteByIdWithSon(19);
}

function addNode(){
    $("#node-parent-id").val($("#node-id").val());
    $("#node-id").val('');
    $("#node-content").val('');
    $("#node-user-type").val('');
    $("#node-question-type").val('');
}