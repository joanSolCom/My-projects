<?php

	require_once "./simplehtmldom/simple_html_dom.php";

	//http://opinionator.blogs.nytimes.com/
	$baseUrl = 'http://opinionator.blogs.nytimes.com/page/';
	$page = 500;

	$db = new PDO('mysql:host=localhost;dbname=blogcrawler', 'root', '1234');
	
	$aInfoPosts = array();

	while ($page < 578){
		$html = file_get_html($baseUrl.$page);
		$elements = $html->find('.post');

		foreach($elements as $element){

			//Extract Title of the article
			foreach($element->find('.entry-title') as $title){
				$aInfoPosts['title'] = $title->plaintext;
			}

			//Extract Author of the article
			foreach($element->find("address a") as $author){
				$aInfoPosts['author'] = $author->plaintext;
			}

			//Extract the links to the full texts
			foreach($element->find('.entry-title a') as $title){
				$aInfoPosts['link'] = $title->href;
			}
			$sql = "INSERT INTO post (author,link,path) VALUES (:author,:link,:path)";
			$q = $db->prepare($sql);
			if($q->execute(array(':author'=>$aInfoPosts['author'],':link'=>$aInfoPosts['link'],':path'=>$aInfoPosts['title']))){

			}
			else{
				$errorcode = $q->errorCode();
				echo $errorcode;
			}
		}
		error_log($page);
		$page++;

		sleep(5);
	}

	
     
    //Completes all the articles content in the array 
	/*foreach($aInfoPosts as $index => $aInfoPost){

		$url = $aInfoPost['link'];
		$html = file_get_html($url);
		$elements = $html->find(".post .entry-content p");
		foreach($elements as $element){
			$text = $text.$element->plaintext;
		}
		$aInfoPosts[$index]['text'] = $text;
	}*/

?>
