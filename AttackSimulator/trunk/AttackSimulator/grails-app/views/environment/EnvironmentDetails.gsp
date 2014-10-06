<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="layout" content="main" />
        <g:javascript src="application.js" />
        <g:javascript src="environment.js"/>
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'environment.css')}" type="text/css">
        <title>Environment Details</title>
    </head>
    <body>
            <div id="content">
                <div id="index">
                    <p class="big">In order to request trial feed from Securonix, please fill out the form below.</p>
                    <hr />
                    <br />
                    <div id="form-fields">
                        <form id=payment method="POST">
                            <fieldset>
                                <legend>Number of Users in your Organization</legend>
                                <ol>
                                    <li>
                                        <label for=name>Number of Users</label>
                                        <input id="name" name="name" type="number" placeholder="1-665" required autofocus>
                                    </li>
                                </ol>
                            </fieldset>
                            <fieldset>
                                <legend>Network Information for your organization</legend>
                                <ol>
                                    <li>
                                        <label for=postcode>Internal IP Address Ranges</label>
                                        <select name = "internal" id = "internalranges">
                                            <option value="classa">Class A (0.0.0.0 - 127.255.255.255)</option>
                                            <option value="classb">Class B (128.0.0.0 - 191.255.255.255)</option>
                                            <option value="classc">Class C (192.0.0.0 - 223.255.255.255)</option>
                                         </select>
                                    </li>
                                    .<li>
                                        <label for=postcode>DMZ IP Address Ranges</label>
                                        <select name = "dmz" id="dmzranges">
                                            <option value="classb">Class B (128.0.0.0 - 191.255.255.255)</option>
                                            <option value="classc">Class C (192.0.0.0 - 223.255.255.255)</option>
                                          </select>
                                    </li>
                                    <li>
                                        <label for=country>External IP address ranges</label>
                                        <g:select id = "type" name="country" value="${countries}" noSelection="${['null':'Select Countries (press ctrl to select multiple)...']}"
                                            from="${countries}"></g:select>
                                    </li>
                                </ol>
                            </fieldset>
                            <fieldset>
                                <button type=submit>Submit</button>
                            </fieldset>
                        </form>
                    </div>

                    <div class="clear"></div>
                </div>

                <div style="clear:both; text-align: center; color: #fe7700; text-decoration: bold; font-size: 15px;" id="errormessages"></div> 
            </div>
    </body>
</html>
