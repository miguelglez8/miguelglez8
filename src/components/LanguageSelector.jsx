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
                {['es', 'en'].map((lang) => (
                    <button
                        key={lang}
                        onClick={() => handleLanguageChange(lang)}
                        style={{
                            padding: '10px 20px',
                            backgroundColor: i18n.language === lang ? '#1e90ff' : '#f4f4f9',
                            color: i18n.language === lang ? 'white' : '#333',
                            border: 'none',
                            borderRadius: '50px',
                            fontSize: '16px',
                            fontWeight: 'bold',
                            cursor: 'pointer',
                            position: 'relative',
                            transition: 'all 0.3s ease'
                        }}
                        onMouseEnter={(e) => {
                            if (i18n.language !== lang) {
                                e.target.style.backgroundColor = '#d0e1ff';
                            }
                        }}
                        onMouseLeave={(e) => {
                            if (i18n.language !== lang) {
                                e.target.style.backgroundColor = '#f4f4f9';
                            }
                        }}
                        data-tooltip={t(`LanguageSelector.${lang}Title`)}
                    >
                        {t(`LanguageSelector.${lang}`)}
                    </button>
                ))}
            </div>
            <style>
                {`
                    button[data-tooltip]:hover::after {
                        content: attr(data-tooltip);
                        position: absolute;
                        bottom: 50px;
                        left: 50%;
                        transform: translateX(-50%);
                        background-color: #1e90ff;
                        color: white;
                        padding: 5px 10px;
                        border-radius: 5px;
                        font-size: 12px;
                        white-space: nowrap;
                    }
                `}
            </style>
        </div>
    );
};

export default LanguageSelector;