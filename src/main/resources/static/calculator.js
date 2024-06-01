let row_count = 1;
function addRow()
{
    ++row_count;
    let text_line = '<tr onchange="getPercent(this)" >\
                    <td>Activity ' + row_count + '</td>\
                    <td>A' + row_count + '</td>\
                    <td class="input_td">\
                        <input type="number" id="weight'+row_count + '" min="0" />\
                    </td>\
                    <td onchange="getPercent(' + row_count + ')" class="input_td">\
                        <input class="random" id="score'+row_count + '" type="number" min="0" /> /\
                        <input type="number" id="total'+row_count + '" min="0" />\
                    </td>\
                    <td>\
                        <p id="percent' + row_count + '"></p>\
                    </td>\
                </tr>';
                
    let newRow = document.createElement("tr");
    let table_body = document.getElementById("table_body");
    newRow.innerHTML = text_line;
    table_body.append(newRow);
}
addRow();

function getMean()
{

    var score_part = [];
    var total_part = [];
    for (let i = 0; i < row_count; i++)
    {
        let score = document.getElementById('score'+(i+1)).value;
        let total = document.getElementById('total'+(i+1)).value;

        if (score=="")
        {
            var index = i;
            ++index;
            alert("missing input values " + index);
        }
        else if(total=="")
        {
            var index = i;
            ++index;
            alert("missing input values " + index);

        }
        else if(total == 0)
        {
            var index = i;
            ++index;
            alert("total can't be zero, please input a different value");
        }
        total_part[i] = total;
        score_part[i] = score;
        
    }
    var mean = 0;
    for (let i = 0; i < row_count; i++)
        mean += score_part[i] / total_part[i];
    
     document.getElementById("result").innerHTML = "Mean is "+(mean/row_count).toFixed(2)*100+"%";
}

function getWeight()
{
    var score_part = [];
    var total_part = [];
    var weight_part = [];
    for (let i = 0; i < row_count; i++)
    {
        var points = document.getElementById('score'+(i+1)).value;
        var total_points = document.getElementById('total'+(i+1)).value;
        var get_weight = document.getElementById('weight'+(i+1)).value;

        if (get_weight == "" || points == "" || total_points == "")
        {
            var index = i;
            ++index;
            alert("Please input all values for Activity " + index + " or delete empty activities");
        }

        if (total_points == 0)
        {
            var index = i;
            ++index;
            alert("Total for Activity " + index + " cannot be 0");
            break;
        }

        score_part[i] = points;
        total_part[i] = total_points;
        weight_part[i] = get_weight;
    }

    var total_weight = 0.0;
    var weight = 0.0;
    for (let i = 0; i < row_count; i++)
    {
        weight += ((score_part[i] / total_part[i])*weight_part[i]);
        total_weight += parseInt(weight_part[i]);
    }
    document.getElementById("result").innerHTML = " Weight is " + (weight/total_weight).toFixed(2)*100+"%";
}


function getPercent(x)
{
    var score = document.getElementById("score"+x).value;
    var total = document.getElementById("total"+x).value;

    if (score == "" || total == "")
     document.getElementById("percent"+x).innerHTML = "";
    
    document.getElementById("percent"+x).innerHTML = ((score*100)/(total)).toFixed(2) + "%";
}