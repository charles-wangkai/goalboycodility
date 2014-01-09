// ==UserScript==
// @name        codility solved problem marker
// @namespace   codility
// @description mark solved problems in green
// @include     http://codility.com/train/
// @version     1
// @require     http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js
// @grant       GM_xmlhttpRequest
// ==/UserScript==

$(document).ready(function() {
  var capitaliseFirstLetter = function(str) {
    return str.charAt(0).toUpperCase() + str.slice(1);
  };
  
  var markSolvedProblem = function($problemName) {
    var name = $.map($problemName.html().trim().split('-'), capitaliseFirstLetter).join('');
    var url = 'http://goalboycodility.googlecode.com/svn/trunk/' + name + '/';
    GM_xmlhttpRequest({
      method: 'GET',
      url: url,
      onload: function(resp) {
        if (resp.status === 200) {
          $problemName.css('background-color', 'lightgreen');
        }
      }
    });
  };
  
  var $problemNames = $('a').filter(function() {
    return $(this).attr('href').indexOf('/demo/take-sample-test/') >= 0;
  });
  
  $.each($problemNames, function(index, value) {
    markSolvedProblem($(value));
  });
});
