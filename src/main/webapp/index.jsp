<!DOCTYPE html>
<html>
<head>
    <style>
        * {
            box-sizing: border-box;
        }

        input[type=text], select, textarea {
            width: 100%;
            padding: 12px;
            border: 1px solid #ccc;
            border-radius: 4px;
            resize: vertical;
        }

        label {
            padding: 12px 12px 12px 0;
            display: inline-block;
        }

        input[type=lastRecords] {
            background-color: #4CAF50;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            float: left;
        }

        input[type=submit] {
            background-color: #4CAF50;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            float: right;
        }

        input[type=lastRecords]:hover {
            background-color: #45a049;
        }

        input[type=submit]:hover {
            background-color: #45a049;
        }

        .container {
            border-radius: 5px;
            background-color: #f2f2f2;
            padding: 20px;
        }

        .col-25 {
            float: left;
            width: 25%;
            margin-top: 6px;
        }

        .col-75 {
            float: left;
            width: 75%;
            margin-top: 6px;
        }

        /* Clear floats after the columns */
        .row:after {
            content: "";
            display: table;
            clear: both;
        }
    </style>
</head>
<body>

<h2>ICAO converter</h2>

<div class="container">
    <form action="/action_page.php">
        <div class="row">
            <div class="col-25">
                <label for="yourText">Your text</label>
            </div>
            <div class="col-75">
                <input type="text" id="yourText" name="yourText" placeholder="Your text..">
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="type">Type</label>
            </div>
            <div class="col-75">
                <select id="type" name="type">
                    <option value="icao">ICAO</option>
                    <option value="fonetic">Fonetic ICAO (polish)</option>
                </select>
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="subject">Output</label>
            </div>
            <div class="col-75">
                <textarea id="subject" name="subject" placeholder="Output" style="height:200px"></textarea>
            </div>
        </div>
        <div class="row">
            <input type="lastRecords" value="Last 10 records">
            <input type="submit" value="Submit">
        </div>
    </form>
</div>

</body>
</html>
