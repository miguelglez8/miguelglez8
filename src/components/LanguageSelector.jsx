import { useTranslation } from 'react-i18next';

const LanguageSelector = () => {
    const { t, i18n } = useTranslation();

    const handleLanguageChange = (lang) => {
        i18n.changeLanguage(lang);
    };

    return (
        <div
            style={{
                position: 'fixed',
                bottom: '20px',
                right: '20px',
                display: 'flex',
                justifyContent: 'center',
                alignItems: 'center',
                backgroundColor: 'transparent',
                zIndex: 1000,
            }}
        >
            <div
                style={{
                    display: 'flex',
                    gap: '15px',
                    padding: '10px',
                    borderRadius: '30px',
                    backgroundColor: '#ccc',
                    alignItems: 'center',
                    justifyContent: 'center',
                }}
            >
                <button
                    onClick={() => handleLanguageChange('es')}
                    title={t('LanguageSelector.spanishTitle')}
                    style={{
                        padding: '10px 20px',
                        backgroundColor: i18n.language === 'es' ? '#1e90ff' : '#f4f4f9',
                        color: i18n.language === 'es' ? 'white' : '#333',
                        border: 'none',
                        borderRadius: '50px',
                        fontSize: '16px',
                        fontWeight: 'bold',
                        cursor: 'pointer',
                        position: 'relative',
                        transition: 'all 0.3s ease',
                    }}
                >
                    {t('LanguageSelector.spanish')}
                    {i18n.language === 'es' && (
                        <span
                            style={{
                                position: 'absolute',
                                top: '-5px',
                                right: '-5px',
                                width: '15px',
                                height: '15px',
                                borderRadius: '50%',
                                backgroundColor: 'white',
                                border: '2px solid #1e90ff',
                            }}
                        />
                    )}
                </button>

                <button
                    onClick={() => handleLanguageChange('en')}
                    title={t('LanguageSelector.englishTitle')}
                    style={{
                        padding: '10px 20px',
                        backgroundColor: i18n.language === 'en' ? '#1e90ff' : '#f4f4f9',
                        color: i18n.language === 'en' ? 'white' : '#333',
                        border: 'none',
                        borderRadius: '50px',
                        fontSize: '16px',
                        fontWeight: 'bold',
                        cursor: 'pointer',
                        position: 'relative',
                        transition: 'all 0.3s ease',
                    }}
                >
                    {t('LanguageSelector.english')}
                    {i18n.language === 'en' && (
                        <span
                            style={{
                                position: 'absolute',
                                top: '-5px',
                                right: '-5px',
                                width: '15px',
                                height: '15px',
                                borderRadius: '50%',
                                backgroundColor: 'white',
                                border: '2px solid #1e90ff',
                            }}
                        />
                    )}
                </button>
            </div>
        </div>
    );
};

export default LanguageSelector;