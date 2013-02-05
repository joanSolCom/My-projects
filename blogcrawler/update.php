<?php
	require_once "./simplehtmldom/simple_html_dom.php";


	$baseUrl = 'http://opinionator.blogs.nytimes.com/page/';
	$page = 1;

	$db = new PDO('mysql:host=localhost;dbname=blogcrawler', 'root', '1234');
	
	$aInfoPosts = array();

	
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

		$sql = "SELECT * FROM post WHERE link= :link";
		$q = $db->prepare($sql);
		$q->execute(array(':link'=>$aInfoPosts['link']));

		$elem = $q->fetch(PDO::FETCH_ASSOC);

		//existing post
		if($elem != NULL){
			echo("NOT INSERTING\n");
		}
		//new post
		else{
			echo("INSERTING\n");
			$sql = "INSERT INTO post (author,link,path) VALUES (:author,:link,:path)";
			$q = $db->prepare($sql);
			if($q->execute(array(':author'=>$aInfoPosts['author'],':link'=>$aInfoPosts['link'],':path'=>$aInfoPosts['title']))){

			}
			else{
				$errorcode = $q->errorCode();
				echo $errorcode;
			}
		}


		
	}


	


?>
