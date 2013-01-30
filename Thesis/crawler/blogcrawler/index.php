<?php

	require_once "./simplehtmldom/simple_html_dom.php";

	//http://opinionator.blogs.nytimes.com/
	$baseUrl = 'http://opinionator.blogs.nytimes.com/page/';
	$page = 1;
	$html = file_get_html($baseUrl.$page);
	

	$db = new PDO('mysql:host=localhost;dbname=blogcrawler', 'root', '1234');
	
	$aInfoPosts = array();
	$i = 0;

	$elements = $html->find('.post');
	foreach($elements as $element){

		//Extract Title of the article
		foreach($element->find('.entry-title') as $title){
			$aInfoPosts[$i]['title'] = $title->plaintext;
		}

		//Extract Author of the article
		foreach($element->find("address a") as $author){
			$aInfoPosts[$i]['author'] = $author->plaintext;
		}

		//Extract the links to the full texts
		foreach($element->find('.entry-title a') as $title){
			$aInfoPosts[$i]['link'] = $title->href;
		}
		$i++;
	}
     
    //Completes all the articles content in the array 
	foreach($aInfoPosts as $index => $aInfoPost){

		$url = $aInfoPost['link'];
		$html = file_get_html($url);
		$elements = $html->find(".post .entry-content p");
		foreach($elements as $element){
			$text = $text.$element->plaintext;
		}
		$aInfoPosts[$index]['text'] = $text;
	}
	var_dump($aInfoPosts);
?>
