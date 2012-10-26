/* Greybox Redux
 * Required: http://jquery.com/
 * Written by: John Resig
 * Amended by Philip Searle to support multiple open greyboxes, March 2011
 * Based on code by: 4mir Salihefendic (http://amix.dk)
 * License: LGPL (read more in LGPL.txt)
 */

/** A stack of active greyboxes. */
var GB_greyboxes = [];

/** Incrementing counter for generating greybox window IDs'. */
var GB_nextId = 0;

GB_show = function(caption, url, height, width) {
	if (window != window.top) {
		return window.top.GB_show(caption, url, height, width);
	}
	
	var $body = $(document.body);
	
	// Ensure the background overlay exists
	if (document.getElementById('GB_overlay') === null) {
		$body.append('<div id="GB_overlay"></div>');
	}
	
	// Allocate an ID unique to this top-level window
	var id = 'GB_window_' + GB_nextId++;
	GB_greyboxes.push(id);
	
	// Create the new greybox window and caption
	$body.append('<div class="GB_window" id="' + id + '">' + 
			'<div class="GB_caption"></div>' + 
			'<img src="/system-webapp/greybox2/close.gif" title="Close window" onclick="GB_hide();" /></div>');
	
	// Create the iframe and set the caption; ensure the overlay is visible
	$('#GB_overlay').show();
	var $greybox = $('#' + id);
	$greybox.append('<iframe class="GB_frame" src="' + url + '"></iframe>');
	$greybox.children('.GB_caption').html(caption);
	
	// Finally, set the size and display it
	$greybox.css({ width: width + 'px', height: height + 'px', marginLeft: (-width / 2) + 'px' });
	$greybox.children('.GB_frame').css('height', (height - 32) + 'px')
	$greybox.slideDown('slow');
	
	// If we are not the top-level greybox then hide our immediate parent
	if (GB_greyboxes.length > 1) {
		var $parent = $('#' + GB_greyboxes[GB_greyboxes.length - 2]);
		$parent.hide();
	}
};

GB_hide = function() {
	// Code using greyboxCloseEvent expects it to be called in the current window
	if (typeof window.greyboxCloseEvent === 'function') {
		greyboxCloseEvent();
	}
	
	if (window != window.top) {
		return window.top.GB_hide();
	}
	
	var $greybox = $('#' + GB_greyboxes.pop());
	$greybox.remove();

	// If weren't the top-level greybox then show our immediate parent, else remove the overlay
	if (GB_greyboxes.length === 0) {
		$('#GB_overlay').hide();
	} else {
		var $parent = $('#' + GB_greyboxes[GB_greyboxes.length - 1]);
		$parent.show();
	}
};

/** This should actually be called without any parameters.
    The myWindow parameter is an implementation detail used to forward execution to the top-level window. */
GB_parent = function(myWindow) {
	if (window != window.top) {
		return window.top.GB_parent(window);
	}
	
	for (var i = 0; i < GB_greyboxes.length; i++) {
		var greybox = $('#' + GB_greyboxes[i] + '>.GB_frame').get(0);
		if (greybox.contentWindow != myWindow) {
			continue;
		}
		if (i == 0) {
			return document;
		}
		return $('#' + GB_greyboxes[i-1] + '>.GB_frame').get(0).contentWindow.document;
	}
};
