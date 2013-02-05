<?php

	require_once "./simplehtmldom/simple_html_dom.php";

	$db = new PDO('mysql:host=localhost;dbname=blogcrawler', 'root', '1234');


	$sql = "SELECT * FROM post WHERE crawled=0 LIMIT 1";
	$q = $db->prepare($sql);
	$q->execute();

	$elem = $q->fetch(PDO::FETCH_ASSOC);

	//existing post
	if($elem == NULL){
		echo("NO MORE POSTS\n");
	}
	else{

		$html = file_get_html($elem['link']);
		$text = '';

		$elements = $html->find(".post .entry-content p");
		foreach($elements as $element){
			$text = $text.$element->plaintext;
		}

		$text = htmlentities($text, ENT_QUOTES, 'UTF-8');

		/*Store text into a file*/
	}

	

?>