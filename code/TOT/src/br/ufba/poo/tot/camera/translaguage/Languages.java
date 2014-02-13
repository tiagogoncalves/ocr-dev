package br.ufba.poo.tot.camera.translaguage;

import br.ufba.poo.tot.R;

import com.memetix.mst.language.Language;

public interface Languages {

	public final String ENGLISH = "eng";
	public final String ITALIAN = "ita";
	public final String CHINESE_TAIWAN = "chi_tra";
	public final String CHINESE_MAINLAND = "chi_sim";
	public final String SWEDISH = "swe";
	public final String ROMANIAN = "ron";
	public final String SLOVENIAN = "slv";
	public final String SERBIAN = "srp";
	public final String TAGALOG = "tgl";
	public final String TURKISH = "tur";
	public final String HUNGARIAN = "hun";
	public final String FINNISH = "fin";
	public final String DUTCH = "ndl";
	public final String NORWEGIAN = "nor";
	public final String JAPANESE = "jpn";
	public final String VIETNAMESE = "vie";
	public final String SPANISH = "spa";
	public final String UKRAINIAN = "ukr";
	public final String FRENCH = "fra";
	public final String SLOVAKIAN = "slk";
	public final String KOREAN = "kor";
	public final String GREEK = "ell";
	public final String RUSSIAN = "rus";
	public final String PORTUGUESE = "por";
	public final String BULGARIAN = "bul";
	public final String LATVIAN = "lav";
	public final String POLISH = "pol";
	public final String DANISH_FRAKTUR = "dan-frak";
	public final String GERMAN = "deu";
	public final String DANISH = "dan";
	public final String CZECH = "ces";
	
	public final long[] mLanguageSizes = {
			2262878,41427474,56148108,2660774,
			2406882,2349867,1926792,2519376,
			2292872,2395687,2506528,2481034,
			2065902,2434628,30716399,13602248,
			2521366,2561262,2388618,2703080,
			2287652,2352918,2398883,2722814,
			2401644,2281434,2387005,2267143,
			2508079,3668480
	};

	public final String[] mLanguages = {
			"bul.traineddata.gz",	//Bulgarian
			"chi_sim.traineddata.gz",	//Chinese simplified
			"chi_tra.traineddata.gz",	//Chinese trad
			"ces.traineddata.gz",	//Czech
			"dan.traineddata.gz",	//danish
			"nld.traineddata.gz",	//Dutch
			"eng.traineddata.gz",	//English
			"fin.traineddata.gz",	//Finish
			"fra.traineddata.gz",	//French
			"deu.traineddata.gz",	//German
			"ell.traineddata.gz",	//Greek
			"hun.traineddata.gz",	//Hungarian
			"ind.traineddata.gz",	//indionesian
			"ita.traineddata.gz",	//italian
			"jpn.traineddata.gz",	//japanese
			"kor.traineddata.gz",	//korean
			"lav.traineddata.gz",	//Latvian
			"lit.traineddata.gz",	//Lithuanian
			"nor.traineddata.gz",	//norwegian
			"pol.traineddata.gz",	//polish
			"por.traineddata.gz",	//portuguese			
			"ron.traineddata.gz",	//romanian
			"rus.traineddata.gz",	//rusian
			"slk.traineddata.gz",	//slovak
			"slv.traineddata.gz",	//slovenian
			"spa.traineddata.gz",	//spanish
			"swe.traineddata.gz",	//swedish
			"tur.traineddata.gz",	//turkish
			"ukr.traineddata.gz",	//ukranian
			"vie.traineddata.gz"	//vietnamese
	};

	public final String[] mLanguages2 = {
			"bul.traineddata",	//Bulgarian
			"chi_sim.traineddata",	//Chinese simplified
			"chi_tra.traineddata",	//Chinese trad
			"ces.traineddata",	//Czech
			"dan.traineddata",	//danish
			"nld.traineddata",	//Dutch
			"eng.traineddata",	//English
			"fin.traineddata",	//Finish
			"fra.traineddata",	//French
			"deu.traineddata",	//German
			"ell.traineddata",	//Greek
			"hun.traineddata",	//Hungarian
			"ind.traineddata",	//indionesian
			"ita.traineddata",	//italian
			"jpn.traineddata",	//japanese
			"kor.traineddata",	//korean
			"lav.traineddata",	//Latvian
			"lit.traineddata",	//Lithuanian
			"nor.traineddata",	//norwegian
			"pol.traineddata",	//polish
			"por.traineddata",	//portuguese			
			"ron.traineddata",	//romanian
			"rus.traineddata",	//rusian
			"slk.traineddata",	//slovak
			"slv.traineddata",	//slovenian
			"spa.traineddata",	//spanish
			"swe.traineddata",	//swedish
			"tur.traineddata",	//turkish
			"ukr.traineddata",	//ukranian
			"vie.traineddata"	//vietnamese
	};
	public final String[] mFromLanguagesTESS ={
			"bul",	//Bulgarian
			"chi_sim",	//Chinese simplified
			"chi_tra",	//Chinese trad
			"ces",	//Czech
			"dan",	//danish
			"nld",	//Dutch
			"eng",	//English
			"fin",	//Finish
			"fra",	//French
			"deu",	//German
			"ell",	//Greek
			"hun",	//Hungarian
			"ind",	//indionesian
			"ita",	//italian
			"jpn",	//japanese
			"kor",	//korean
			"lav",	//Latvian
			"lit",	//Lithuanian
			"nor",	//norwegian
			"pol",	//polish
			"por",	//portuguese			
			"ron",	//romanian
			"rus",	//rusian
			"slk",	//slovak
			"slv",	//slovenian
			"spa",	//spanish
			"swe",	//swedish
			"tur",	//turkish
			"ukr",	//ukranian
			"vie"	//vietnamese
	};
	
	public final Integer[] mFromLanguageBox = {
			R.drawable.from_language_bulgarian,
			R.drawable.from_language_chinese,R.drawable.from_language_chinese,
			R.drawable.from_language_czech,R.drawable.from_language_danish,
			R.drawable.from_language_dutch,R.drawable.from_language_english,
			R.drawable.from_language_finnish,
			R.drawable.from_language_french,R.drawable.from_language_german,
			R.drawable.from_language_greek,
			R.drawable.from_language_hungarian,R.drawable.from_language_indonesian,
			R.drawable.from_language_italian,R.drawable.from_language_japanese,
			R.drawable.from_language_korean,R.drawable.from_language_lavtian,R.drawable.from_language_lithuanian,
			R.drawable.from_language_norwegian,R.drawable.from_language_polish,
			R.drawable.from_language_portuguese,R.drawable.from_language_romanian,
			R.drawable.from_language_russian,R.drawable.from_language_slovak,
			R.drawable.from_language_slovanian,R.drawable.from_language_spanish,
			R.drawable.from_language_swedish,R.drawable.from_language_turkish,
			R.drawable.from_language_ukranian,
			R.drawable.from_language_vietnamese
	};
	
	public final Language[] mFromLanguagesBing ={
			Language.BULGARIAN,	//Bulgarian
			Language.CHINESE_SIMPLIFIED,	//Chinese simplified
			Language.CHINESE_TRADITIONAL,	//Chinese trad
			Language.CZECH,	//Czech
			Language.DANISH,	//danish
			Language.DUTCH,	//Dutch
			Language.ENGLISH,	//English
			Language.FINNISH,	//Finish
			Language.FRENCH,
			Language.GERMAN,
			Language.GREEK,
			Language.HUNGARIAN,	//Hungarian
			Language.INDONESIAN,	//indionesian
			Language.ITALIAN,	//italian
			Language.JAPANESE,	//japanese
			Language.KOREAN,	//korean
			Language.LATVIAN,	//Latvian
			Language.LITHUANIAN,	//Lithuanian
			Language.NORWEGIAN,	//norwegian
			Language.POLISH,	//polish
			Language.PORTUGUESE,	//portuguese			
			Language.ROMANIAN,	//romanian
			Language.RUSSIAN,	//rusian
			Language.SLOVAK,	//slovak
			Language.SLOVENIAN,	//slovenian
			Language.SPANISH,	//spanish
			Language.SWEDISH,	//swedish
			Language.TURKISH,	//turkish
			Language.UKRANIAN,	//ukranian
			Language.VIETNAMESE	//vietnamese
	};
	
	public final Integer[] mToLanguageBox = {
			R.drawable.to_language_arabic,R.drawable.to_language_bulgarian,
			R.drawable.to_language_chinese,R.drawable.to_language_chinese,
			R.drawable.to_language_czech,R.drawable.to_language_dannish,
			R.drawable.to_language_dutch,R.drawable.to_language_english,
			R.drawable.to_language_estonian,R.drawable.to_language_finnish,
			R.drawable.to_language_french,R.drawable.to_language_german,
			R.drawable.to_language_greek,R.drawable.to_language_hebrew,
			R.drawable.to_language_hungarian,R.drawable.to_language_indonesian,
			R.drawable.to_language_italian,R.drawable.to_language_japanese,
			R.drawable.to_language_korean,R.drawable.to_language_latvian,
			R.drawable.to_language_lithuanian,
			R.drawable.to_language_norwegian,R.drawable.to_language_polish,
			R.drawable.to_language_portuguese,R.drawable.to_language_romanian,
			R.drawable.to_language_russian,R.drawable.to_language_slovak,
			R.drawable.to_language_slovanian,R.drawable.to_language_spanish,
			R.drawable.to_language_swedish,R.drawable.to_language_turkish,
			R.drawable.to_language_thai,R.drawable.to_language_ukranian,
			R.drawable.to_language_vietnamese
		};

	public final Language[] mToLanguages = {
			Language.ARABIC,	//arabic
			Language.BULGARIAN,
			Language.CHINESE_SIMPLIFIED,
			Language.CHINESE_TRADITIONAL,
			Language.CZECH,
			Language.DANISH,
			Language.DUTCH,
			Language.ENGLISH,
			Language.ESTONIAN,
			Language.FINNISH,
			Language.FRENCH,
			Language.GERMAN,
			Language.GREEK,
			Language.HEBREW,
			Language.HUNGARIAN,
			Language.INDONESIAN,
			Language.ITALIAN,
			Language.JAPANESE,
			Language.KOREAN,
			Language.LATVIAN,
			Language.LITHUANIAN,
			Language.NORWEGIAN,
			Language.POLISH,
			Language.PORTUGUESE,
			Language.ROMANIAN,
			Language.RUSSIAN,
			Language.SLOVAK,
			Language.SLOVENIAN,
			Language.SPANISH,
			Language.SWEDISH,
			Language.THAI,
			Language.TURKISH,
			Language.UKRANIAN,
			Language.VIETNAMESE
	};

}
