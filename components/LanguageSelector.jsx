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
                marginRight: '15px',
                marginBottom: '10px'
            }}
        >
            <div
                style={{
                    display: 'flex',
                    gap: '10px',
                    padding: '8px',
                    borderRadius: '30px',
                    backgroundColor: '#ccc',
                    alignItems: 'center',
                    justifyContent: 'center',
                    flexWrap: 'wrap',
                    maxWidth: '95%',
                }}
            >
                {['es', 'en'].map((lang) => (
                    <button
                        key={lang}
                        onClick={() => handleLanguageChange(lang)}
                        style={{
                            padding: '8px 16px',
                            backgroundColor: i18n.language === lang ? '#1e90ff' : '#f4f4f9',
                            color: i18n.language === lang ? 'white' : '#333',
                            border: 'none',
                            borderRadius: '50px',
                            fontSize: '14px',
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
                        bottom: 40px;
                        left: 50%;
                        transform: translateX(-50%);
                        background-color: #1e90ff;
                        color: white;
                        padding: 5px 10px;
                        border-radius: 5px;
                        font-size: 12px;
                        white-space: nowrap;
                    }

                    @media (max-width: 768px) {
                        div {
                            bottom: 10px !important;
                            right: 10px !important;
                        }
                        button {
                            font-size: 12px !important;
                            padding: 6px 12px !important;
                        }
                    }
                `}
            </style>
        </div>
    );
};

export default LanguageSelector;