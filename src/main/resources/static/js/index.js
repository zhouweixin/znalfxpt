var urlFindProcessRoot = "/znalfxpt/process/findProcessRoot";
var urlAddProcess = "/znalfxpt/process/add";
var urlDeleteById = "/znalfxpt/process/deleteById";
var urlDeleteByIdWithSon = "/znalfxpt/process/deleteByIdWithSon";

function deleteById (id) {
    $.ajax({
        url: urlDeleteById,
        data: {id:id},
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

function deleteByIdWithSon (id) {
    $.ajax({
        url: urlDeleteByIdWithSon,
        data: {id:id},
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

function addProcess (data) {
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
function parseData(data) {
    if (data == null) {
        return data;
    }

    data.value = data.id;
    data.name1 = data.name;
    data.name = data.content;
    data.children = data.processes;
    data.processes = null;

    for (var i = 0; i < data.children.length; i++) {
        data.children[i] = parseData(data.children[i])
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
        }
    );
}