<?php

	require_once "./simplehtmldom/simple_html_dom.php";

	//http://opinionator.blogs.nytimes.com/

	$html = file_get_html('http://opinionator.blogs.nytimes.com');

	$db = new PDO('mysql:host=localhost;dbname=blogcrawler', 'root', '1234');
	//$stmt = $db->prepare("select contenttype, imagedata from images where id=?");
	
	$elements = $html->find('.post');
	foreach($elements as $element){

		//Extract Title of the article
		foreach($element->find('.entry-title') as $title){
			echo $title->plaintext . "<br/>";
		}

		//Extract Author of the article
		foreach($element->find("address a") as $author){
			echo $author->plaintext . "<br/>";
		}
	}
       
?>
